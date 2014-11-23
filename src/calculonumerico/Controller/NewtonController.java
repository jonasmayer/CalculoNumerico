/*-
 * Classname:             NewtonController.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  08/10/2012 - 16:09:21
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      COPYRIGHT 2012 Jonas Mayer
 */

/*-
 *    Este programa é um software livre; você pode redistribui-lo e/ou
 *    modifica-lo dentro dos termos da Licença Pública Geral GNU como
 *    publicada pela Fundação do Software Livre (FSF); na versão 2 da
 *    Licença, ou qualquer versão.
 *
 *    Este programa é distribuido na esperança que possa ser util,
 *    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
 *    MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *    Licença Pública Geral GNU para maiores detalhes.
 *
 *    Você deve ter recebido uma cópia da Licença Pública Geral GNU
 *    junto com este programa, se não, escreva para a Fundação do Software
 *    Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package calculonumerico.Controller;

import calculonumerico.MainMenu;
import calculonumerico.Model.NewtonModel;
import calculonumerico.View.NewtonView;
import calculonumerico.View.ResultView;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controle da calculadora do método de Newton
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class NewtonController {

    //visão
    private NewtonView newtonView;
    //modelo
    private ResultView resultView;

    /**
     * Construtor sem parâmetros
     */
    public NewtonController() {

        //inicializa componentes
        newtonView = new NewtonView();
        //define eventos
        newtonView.setCEActionListener();
        newtonView.setEraseActionListener();
        newtonView.setPanelDigitsListeners();
        newtonView.setReturnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newtonView.eraseCE();
                newtonView.close();
                MainMenu mainMenu = new MainMenu();
                if (resultView != null) {
                    resultView.close();
                }
            }
        });
        newtonView.setResultActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!newtonView.getInterval1().isEmpty() && !newtonView.getTOL().isEmpty()) {
                    calculeResult();
                } else {
                    if (newtonView.getInterval1().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Informe o intervalo!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Informe o TOL!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
        });
    }//fim do construtor sem parâmetros

    /**
     * Calcula resultado e passa resultado para visão
     */
    private void calculeResult() {
        if (newtonView.getCountList() > 0) {
            try {
                String exp = newtonView.calcule();
                double x = Double.valueOf(newtonView.getInterval1());
                double tol = Double.valueOf(newtonView.getTOL());
                NewtonModel newtonModel = new NewtonModel(exp, x, tol);
                //calcula resultado e abre tabela com resultado
                List<Object[]> model = new ArrayList();

                //preenche modelo
                double x1 = 0;
                while (!newtonModel.getSent()) {
                    newtonModel.calcule();
                    int i = newtonModel.getCount();
                    x1 = newtonModel.getX();
                    model.add(new Object[]{i + 1, x1});
                    newtonModel.next();
                }
                if (resultView != null) {
                    resultView.close();
                }
                resultView = new ResultView(model);
                JOptionPane.showMessageDialog(null, x1, "Resultado!", JOptionPane.PLAIN_MESSAGE);


            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(null, "Valores de Intervalo incompativeis");
                exception.printStackTrace();
            } catch (UnknownFunctionException ex) {
                JOptionPane.showMessageDialog(null, "ERRO", "Erro ao calcular função!", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (UnparsableExpressionException ex) {
                JOptionPane.showMessageDialog(null, "ERRO", "Erro ao calcular função!", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valores de Intervalo incompativeis");
                ex.printStackTrace();
            }

        }
    }//fim do método calculeResult

    /**
     * Atualiza dados com dados da calculadora de intervalos
     *
     * @param fxList função
     * @param visualFxList função visual
     * @param countList contador
     * @param countListVisualcontador visual
     * @param x1 intervalo
     */
    public void setInterval(List fxList, List visualFxList, int countList, int countListVisual, int x1) {
        newtonView.setFxList(fxList);
        newtonView.setVisualFxList(visualFxList);
        newtonView.setCountList(countList);
        newtonView.setCountListVisual(countListVisual);
        newtonView.setA(x1);
    }//fim do método setInterval
}//fim da classe NewtonController 

