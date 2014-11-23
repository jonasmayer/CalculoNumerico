/*-
 * Classname:             MainMenu.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  29/10/2012 - 16:14:36
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      COPYRIGHT 2012 Jonas Mayer
 */

/*-
 *    Este program|a é um software livre; você pode redistribui-lo e/ou
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
package calculonumerico;

import calculonumerico.Controller.BisectionController;
import calculonumerico.Controller.IntervalController;
import calculonumerico.Controller.NewtonController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Menu Principal para seleção de calculadora
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class MainMenu {

    //frame principal do menu
    private JFrame frame;
    //botão que a calculadora de intervalos
    private JButton intervalButton;
    //botão que a calculadora do método de bisscção
    private JButton bisectionButton;
    //botão que a calculadora do método de Newton
    private JButton newtonButton;
    //panel com imagem
    private JLabel imageLabel;

    /**
     * Construtor sem parâmetros
     */
    public MainMenu() {
        createView();
        setEvent();
    }//fim do construtor

    /**
     * Cria a visão do menu Principal
     */
    private void createView() {

        //inicializa frame
        frame = new JFrame("Cálculo Numérico");
        frame.setLayout(null);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        //define visual
        frame.getContentPane().setBackground(Color.WHITE);

        //botões
        intervalButton = new JButton("Intervalo");
        bisectionButton = new JButton("Bissecção");
        newtonButton = new JButton("Newton");

        //carrega imagem
        String separator = System.getProperty("file.separator");

        ImageIcon img = new ImageIcon("image" + separator + "calculator2.png");

        //panel com imagem
        imageLabel = new JLabel(img);

        //configura tamanho do label de acordo com o tamanho da imagem
        imageLabel.setBounds(15, 50, img.getIconHeight(), img.getIconWidth());

        //define posição e tamanho dos elementos
        intervalButton.setBounds(200, 50, 110, 25);
        bisectionButton.setBounds(200, 85, 110, 25);
        newtonButton.setBounds(200, 120, 110, 25);

        //define cor dos elementos
        intervalButton.setForeground(Color.BLACK);
        bisectionButton.setForeground(Color.BLACK);
        newtonButton.setForeground(Color.BLACK);

        //adiciona componentes
        frame.add(imageLabel);
        frame.add(intervalButton);
        frame.add(bisectionButton);
        frame.add(newtonButton);

        //configurações finais
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }//fim do método createView

    /**
     * Define eventos do frame
     */
    private void setEvent() {

        //evento do botão Intervalo que abre a calculadora
        intervalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deixa o menu principal invisivel
                frame.setVisible(false);
                //abre a calculadora
                IntervalController intervalController = new IntervalController();

            }
        });//fim do evento

        //enento do botão bissecção que abre a calculadora de bissecção
        bisectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deixa o menu principal invisivel
                frame.setVisible(false);
                //abre a calculadora
                BisectionController bisectionController = new BisectionController();
            }
        });//fim do evento

        //evento do botão de newton que abre a calculadora de Newton
        newtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deixa o menu principal invisivel
                frame.setVisible(false);
                //are a calculadora de Newton
                NewtonController newtonController = new NewtonController();
            }
        });//fim do evento

    }//fim do método setEvent

    public static void main(String args[]) {
        MainMenu mainMenu = new MainMenu();
    }
}//fim da classe MainMenu 
