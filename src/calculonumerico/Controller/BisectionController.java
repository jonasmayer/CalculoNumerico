/*-
 * Classname:             BisectionController.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  08/10/2012 - 16:08:20
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
import calculonumerico.Model.BisectionModel;
import calculonumerico.View.BisectionView;
import calculonumerico.View.ResultView;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controle de bissecção
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class BisectionController {

    //view de bissecção
    private BisectionView bisectionView;
    //visão do resultado
    private ResultView resultView;

    /**
     * Construtor sem parametros
     */
    public BisectionController() {

        //inicializa visão
        bisectionView = new BisectionView();
        //define eventos pré-definidos baseando-se na classe herdada
        bisectionView.setCEActionListener();
        bisectionView.setEraseActionListener();
        bisectionView.setPanelDigitsListeners();
        //define ouvinte de evento para o botão retornar
        bisectionView.setReturnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //limpa registros
                bisectionView.eraseCE();
                //fecha view
                bisectionView.close();
                //cria menu principal
                MainMenu mainMenu = new MainMenu();
                //fecha view com resultado se ela existir
                if (resultView != null) {
                    resultView.close();
                }
            }
        });

        //define ouvinte de evento para o botão =
        bisectionView.setResultActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //faz bissecção e cria model
                calculeResult();
            }
        });
    }//fim do construtor

    /**
     * Define função e intervalos do calculo de intervalos
     *
     * @param fxList função visual
     * @param visualFxList função
     * @param countList contador
     * @param countListVisual contador visual
     * @param x1 intervalo a
     * @param x2 intervalo b
     */
    public void setInterval(List fxList, List visualFxList, int countList, int countListVisual, int x1, int x2) {
        bisectionView.setFxList(fxList);
        bisectionView.setVisualFxList(visualFxList);
        bisectionView.setCountList(countList);
        bisectionView.setCountListVisual(countListVisual);
        bisectionView.setA(x1);
        bisectionView.setB(x2);
    }//fim do método setInterval

    /**
     * Listener de evento que pega resultado do calculo e cria model
     */
    private void calculeResult() {

        //verifica se a lista está vazia
        if (bisectionView.getCountList() > 0) {
            try {
                //transforma lista em string
                String fx = bisectionView.calcule();
                //pega e converte intervalos e TOL de textfields
                double a = Double.parseDouble(bisectionView.getInterval1());
                double b = Double.parseDouble(bisectionView.getInterval2());
                double tol = Double.parseDouble(bisectionView.getTOL());
                //calcula resultado e abre tabela com resultado
                List<Object[]> model = new ArrayList();

                //cria modelo para calculo de bissecção
                BisectionModel bisectionModel = new BisectionModel(fx, a, b, tol);
                //recebe o número de interações
                double n = bisectionModel.getN();

                //calcula até o número limite de interações
                double x = 0;

                for (int i = 0; i < n; i++) {
                    double a2 = bisectionModel.getA();
                    double b2 = bisectionModel.getB();
                    x = bisectionModel.getX();
                    //Modificado - Leugim - Inicio
                    double fa = bisectionModel.calculaF(a);
                    double fb = bisectionModel.calculaF(b);
                    if (fa == 0) {
                        JOptionPane.showMessageDialog(null, "O Extremo Inferior é Raiz da Equação: x = " + a2 + ".", "Oops!!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (fb == 0) {
                        JOptionPane.showMessageDialog(null, "O Extremo Superior é Raiz da Equação: x = " + b2 + ".", "Oops!!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if ((fa * fb) > 0) {
                        JOptionPane.showMessageDialog(null, " - Intervalo não contém uma Raiz; \n - Contém multiplas Raízes; ou \n - Não pode ser Estimado pelo Método da Bissecção! " + fa * fb + ".", "Oops!!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    //Modificado - Leugim - Fim
                    double result = bisectionModel.getResult();
                    model.add(new Object[]{i + 1, x});
                    if (result == 0 || Math.abs(fb - fa) < tol) {
                        n = i;
                    }
                    bisectionModel.calculesNewValues();
                }

                //se um frame de resultados já tiver sido aberto feche-o
                if (resultView != null) {
                    resultView.close();
                }
                //cria visão do resultado com o modelo gerado

                resultView = new ResultView(model);
                JOptionPane.showMessageDialog(null, "Solução Aproximada. x = " + x + " ", "Resultado!", JOptionPane.PLAIN_MESSAGE);


            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(null, "Valores de Intervalo não informados");
            } catch (UnknownFunctionException ex) {
                JOptionPane.showMessageDialog(null, "ERRO", "Erro ao calcular função!", JOptionPane.ERROR_MESSAGE);
            } catch (UnparsableExpressionException ex) {
                JOptionPane.showMessageDialog(null, "ERRO", "Erro ao calcular função!", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valores de Intervalo incompativeis");
            }
        }
    }//fim do método calculeResult
}//fim da classe BisectionController 

