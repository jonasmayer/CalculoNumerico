/*-
 * Classname:             IntervalController.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  08/10/2012 - 16:08:45
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
import calculonumerico.Model.IntervalModel;
import calculonumerico.View.IntervalView;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controle da calculadora de intervalos
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class IntervalController {

    //visão da calculadora de intervalos
    private IntervalView intervalView;

    /**
     * Construtor sem parâmetro
     */
    public IntervalController() {

        //inicializa componentes
        intervalView = new IntervalView();

        //define eventos
        intervalView.setCEActionListener();
        intervalView.setEraseActionListener();
        intervalView.setPanelDigitsListeners();
        intervalView.setReturnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //limpa registros
                intervalView.eraseCE();
                //fecha view
                intervalView.close();
                //cria menu principal
                MainMenu mainMenu = new MainMenu();
            }
        });
        intervalView.setResultActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculeResult();
            }
        });
        intervalView.setActionListenerBisseccao(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (intervalView.getSelectedListIndex() >= 0) {
                    //pega array com intervalo
                    String tempInterval[] = intervalView.getSelectedListValues();
                    if (tempInterval != null) {
                        //transfere intervalos para variaveis
                        int x1 = Integer.parseInt(tempInterval[0]);
                        int x2 = Integer.parseInt(tempInterval[1]);
                        //fecha view
                        intervalView.close();
                        //inicia calculadora de bissecção
                        BisectionController fc = new BisectionController();
                        //passa dados para a calculadora
                        fc.setInterval(intervalView.getFxList(), intervalView.getVisualFxList(), intervalView.getCountList(), intervalView.getCountListVisual(), x1, x2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um intervalo!");
                }
            }
        });
        intervalView.setActionListenerNewton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (intervalView.getSelectedListIndex() >= 0) {

                    String tempInterval[] = intervalView.getSelectedListValues();
                    if (tempInterval != null) {
                        int x1 = Integer.parseInt(tempInterval[0]);
                        int x2 = Integer.parseInt(tempInterval[1]);
                        intervalView.close();
                        int x = x2;
                        if (x2 < 0 && x1 < 0) {
                            x = x1;
                        }
                        NewtonController fc = new NewtonController();
                        fc.setInterval(intervalView.getFxList(), intervalView.getVisualFxList(), intervalView.getCountList(), intervalView.getCountListVisual(), x);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um intervalo!");
                }
            }
        });
    }//fim do construtor

    /**
     * Calcula intervalos
     */
    private void calculeResult() {
        if (intervalView.getCountList() > 0) {
            try {
                IntervalModel im = new IntervalModel(intervalView.calcule());
                List list = im.getList();
                intervalView.setIntervals(list.toArray());
            } catch (UnknownFunctionException ex) {
                JOptionPane.showMessageDialog(null, "Verifique a função!");
            } catch (UnparsableExpressionException ex) {
                JOptionPane.showMessageDialog(null, "Verifique a função!");
            } catch (ClassCastException classCastException) {
                JOptionPane.showMessageDialog(null, "Verifique a função!");
            }
        }
    }//fim do método calculeResult
}//fim da classe IntervalController 
