
/*-
 * Classname:             PanelDigits.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  08/10/2012 - 16:21:44
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
package calculonumerico.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Panel com Painel de digitos
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class PanelDigits {

    //sentinela
    public static boolean watcherLog = false;
    //painel para digitos
    private JPanel pDigits;
    //botoes
    //0
    private JButton b0;
    //1
    private JButton b1;
    //2
    private JButton b2;
    //3
    private JButton b3;
    //4
    private JButton b4;
    //5
    private JButton b5;
    //6
    private JButton b6;
    //7
    private JButton b7;
    //8
    private JButton b8;
    //9
    private JButton b9;
    //cos
    private JButton bCos;
    //sen
    private JButton bSen;
    //tan
    private JButton bTan;
    //acos
    private JButton bAcos;
    //asen
    private JButton bAsen;
    //atan
    private JButton bAtan;
    //(
    private JButton bopenParenthesis;
    //)
    private JButton bcloseParenthesis;
    //virgula
    private JButton bCcomma;
    //euler
    private JButton bEuler;
    //pi
    private JButton bPi;
    //x
    private JButton bEx;
    //denominador
    private JButton bDenominator;
    //expoente
    private JButton bExpoent;
    //CE
    private JButton bCE;
    //erase
    private JButton bErase;
    //sqrt
    private JButton bSqrt;
    //ln
    private JButton bLn;
    //sqrt3
    private JButton bSqrt3;
    //log
    private JButton bLog;
    //+
    private JButton bMore;
    //-
    private JButton bLess;
    // *
    private JButton bMultiplied;
    // /
    private JButton bDivided;
    //result
    private JButton bResult;

    /**
     * Construtor sem parâmetros
     */
    public PanelDigits() {

        //tamanho do painel
        int width = 385;
        int height = 450;
        pDigits = new JPanel();
        pDigits.setSize(width, height);
        pDigits.setVisible(true);
        pDigits.setLayout(null);

        //inicializa componentes
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bCos = new JButton("cos");
        bSen = new JButton("sen");
        bTan = new JButton("tan");
        bAcos = new JButton("acos");
        bAsen = new JButton("asen");
        bAtan = new JButton("atan");
        bopenParenthesis = new JButton("(");
        bcloseParenthesis = new JButton(")");
        bCcomma = new JButton(",");
        bEuler = new JButton("e");
        bPi = new JButton("π");
        bEx = new JButton("X");
        bDenominator = new JButton("1/x");
        bExpoent = new JButton("^");
        bCE = new JButton("CE");
        bErase = new JButton("←");
        bSqrt = new JButton("√");
        bLn = new JButton("ln");
        bSqrt3 = new JButton("∛");
        bLog = new JButton("log");
        bMore = new JButton("+");
        bLess = new JButton("-");
        bMultiplied = new JButton("*");
        bDivided = new JButton("/");
        bResult = new JButton("=");

        //posição das colunas
        int[] x = {10, 85, 160, 235, 310};
        int[] y = {395, 340, 285, 230, 175, 120, 65, 10};

        //coluna 1
        b0.setBounds(x[0], y[0], 140, 45);
        b1.setBounds(x[0], y[1], 65, 45);
        b4.setBounds(x[0], y[2], 65, 45);
        b7.setBounds(x[0], y[3], 65, 45);
        bopenParenthesis.setBounds(x[0], y[4], 65, 45);
        bTan.setBounds(x[0], y[5], 65, 45);
        bSen.setBounds(x[0], y[6], 65, 45);
        bCos.setBounds(x[0], y[7], 60, 45);

        //coluna 2
        b2.setBounds(x[1], y[1], 65, 45);
        b5.setBounds(x[1], y[2], 65, 45);
        b8.setBounds(x[1], y[3], 65, 45);
        bcloseParenthesis.setBounds(x[1], y[4], 65, 45);
        bAtan.setBounds(x[1], y[5], 65, 45);
        bAsen.setBounds(x[1], y[6], 65, 45);
        bAcos.setBounds(x[1], y[7], 65, 45);

        //coluna 3
        bCcomma.setBounds(x[2], y[0], 65, 45);
        b3.setBounds(x[2], y[1], 65, 45);
        b6.setBounds(x[2], y[2], 65, 45);
        b9.setBounds(x[2], y[3], 65, 45);
        bExpoent.setBounds(x[2], y[4], 65, 45);
        bDenominator.setBounds(x[2], y[5], 65, 45);
        bPi.setBounds(x[2], y[6], 65, 45);
        bEuler.setBounds(x[2], y[7], 65, 45);

        //coluna 4
        bEx.setBounds(x[3], y[1], 65, 100);
        bMultiplied.setBounds(x[3], y[2], 65, 45);
        bMore.setBounds(x[3], y[3], 65, 45);
        bLn.setBounds(x[3], y[4], 65, 45);
        bSqrt.setBounds(x[3], y[5], 65, 45);
        bErase.setBounds(x[3], y[6], 140, 45);
        bCE.setBounds(x[3], y[7], 140, 45);

        //coluna 5
        bResult.setBounds(x[4], y[1], 65, 100);
        bDivided.setBounds(x[4], y[2], 65, 45);
        bLess.setBounds(x[4], y[3], 65, 45);
        bLog.setBounds(x[4], y[4], 65, 45);
        bSqrt3.setBounds(x[4], y[5], 65, 45);

        //define opções visuais
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 11);
        b0.setFont(font);
        b1.setFont(font);
        b2.setFont(font);
        b3.setFont(font);
        b4.setFont(font);
        b5.setFont(font);
        b6.setFont(font);
        b7.setFont(font);
        b8.setFont(font);
        b9.setFont(font);
        bCos.setFont(font2);
        bSen.setFont(font2);
        bTan.setFont(font2);
        bAcos.setFont(font2);
        bAsen.setFont(font2);
        bAtan.setFont(font2);
        bopenParenthesis.setFont(font);
        bcloseParenthesis.setFont(font);
        bCcomma.setFont(font);
        bEuler.setFont(font);
        bPi.setFont(font);
        bEx.setFont(font);
        bDenominator.setFont(font);
        bExpoent.setFont(font);
        bCE.setFont(font);
        bErase.setFont(font);
        bSqrt.setFont(font);
        bLn.setFont(font);
        bSqrt3.setFont(font);
        bLog.setFont(font);
        bMore.setFont(font);
        bLess.setFont(font);
        bMultiplied.setFont(font);
        bDivided.setFont(font);
        bResult.setFont(font);

        b0.setForeground(Color.BLACK);
        b1.setForeground(Color.BLACK);
        b2.setForeground(Color.BLACK);
        b3.setForeground(Color.BLACK);
        b4.setForeground(Color.BLACK);
        b5.setForeground(Color.BLACK);
        b6.setForeground(Color.BLACK);
        b7.setForeground(Color.BLACK);
        b8.setForeground(Color.BLACK);
        b9.setForeground(Color.BLACK);
        bCos.setForeground(Color.BLACK);
        bSen.setForeground(Color.BLACK);
        bTan.setForeground(Color.BLACK);
        bAcos.setForeground(Color.BLACK);
        bAsen.setForeground(Color.BLACK);
        bAtan.setForeground(Color.BLACK);
        bopenParenthesis.setForeground(Color.BLACK);
        bcloseParenthesis.setForeground(Color.BLACK);
        bCcomma.setForeground(Color.BLACK);
        bEuler.setForeground(Color.BLACK);
        bPi.setForeground(Color.BLACK);
        bEx.setForeground(Color.BLACK);
        bDenominator.setForeground(Color.BLACK);
        bExpoent.setForeground(Color.BLACK);
        bCE.setForeground(Color.BLACK);
        bErase.setForeground(Color.BLACK);
        bSqrt.setForeground(Color.BLACK);
        bLn.setForeground(Color.BLACK);
        bSqrt3.setForeground(Color.BLACK);
        bLog.setForeground(Color.BLACK);
        bMore.setForeground(Color.BLACK);
        bLess.setForeground(Color.BLACK);
        bMultiplied.setForeground(Color.BLACK);
        bDivided.setForeground(Color.BLACK);;
        bResult.setForeground(Color.BLACK);

        //adiciona ao panel
        pDigits.add(b0);
        pDigits.add(b1);
        pDigits.add(b2);
        pDigits.add(b3);
        pDigits.add(b4);
        pDigits.add(b5);
        pDigits.add(b6);
        pDigits.add(b7);
        pDigits.add(b8);
        pDigits.add(b9);
        pDigits.add(bCos);
        pDigits.add(bSen);
        pDigits.add(bTan);
        pDigits.add(bAcos);
        pDigits.add(bAsen);
        pDigits.add(bAtan);
        pDigits.add(bopenParenthesis);
        pDigits.add(bcloseParenthesis);
        pDigits.add(bCcomma);
        pDigits.add(bEuler);
        pDigits.add(bPi);
        pDigits.add(bEx);
        pDigits.add(bDenominator);
        pDigits.add(bExpoent);
        pDigits.add(bCE);
        pDigits.add(bErase);
        pDigits.add(bSqrt);
        pDigits.add(bLn);
        pDigits.add(bSqrt3);
        pDigits.add(bLog);
        pDigits.add(bMore);
        pDigits.add(bLess);
        pDigits.add(bMultiplied);
        pDigits.add(bDivided);
        pDigits.add(bResult);
    }//fim do método construtor

    /**
     * Retorna o panel
     *
     * @return <code>JPanel</code> painel
     */
    public JPanel getPDigits() {
        return pDigits;
    }//fim do método getPDigits

    /**
     * Define evenntos genéricos
     *
     * @param actionListener ouvinte
     */
    public void setGeneticActionListener(ActionListener actionListener) {
        b0.addActionListener(actionListener);
        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);
        b5.addActionListener(actionListener);
        b6.addActionListener(actionListener);
        b7.addActionListener(actionListener);
        b8.addActionListener(actionListener);
        b9.addActionListener(actionListener);
        bCos.addActionListener(actionListener);
        bSen.addActionListener(actionListener);
        bTan.addActionListener(actionListener);
        bAcos.addActionListener(actionListener);
        bAsen.addActionListener(actionListener);
        bAtan.addActionListener(actionListener);
        bopenParenthesis.addActionListener(actionListener);
        bcloseParenthesis.addActionListener(actionListener);
        bCcomma.addActionListener(actionListener);
        bEuler.addActionListener(actionListener);
        bPi.addActionListener(actionListener);
        bEx.addActionListener(actionListener);
        bDenominator.addActionListener(actionListener);
        bExpoent.addActionListener(actionListener);
        bSqrt.addActionListener(actionListener);
        bLn.addActionListener(actionListener);
        bSqrt3.addActionListener(actionListener);
        bLog.addActionListener(actionListener);
        bMore.addActionListener(actionListener);
        bLess.addActionListener(actionListener);
        bMultiplied.addActionListener(actionListener);
        bDivided.addActionListener(actionListener);
    }//fim do método setGeneticActionListener

    /**
     * Define evento do botão CE
     *
     * @param listener ouvinte
     */
    public void setCEActionListener(ActionListener listener) {
        bCE.addActionListener(listener);
    }//fim do método setCEActionListener

    /**
     * Define evento do botâo resultado
     *
     * @param actionListener ouvninte
     */
    public void setResultListenerActionListener(ActionListener actionListener) {
        bResult.addActionListener(actionListener);
    }//fim do método setResultListenerActionListener

    /**
     * Define evento do botão apagar
     *
     * @param listener ouvinte
     */
    public void setEraseActionListener(ActionListener listener) {
        bErase.addActionListener(listener);
    }//fim do método setEraseActionListener

    /**
     * Retorna os valores do botão pressionado
     *
     * @param event evento
     * @return <code>String[]</code> valores do botão
     */
    public String[] getDigit(ActionEvent event) {

        //código que aparece no textfield
        String codeCalculatorVisual = "";
        //código que vai para a equação
        String codeCalculator = "";

        if (event.getSource() == b0) {
            codeCalculatorVisual = "0";
            codeCalculator = "0";
        }
        if (event.getSource() == b1) {
            codeCalculatorVisual = "1";
            codeCalculator = "1";
        }
        if (event.getSource() == b2) {
            codeCalculatorVisual = "2";
            codeCalculator = "2";
        }
        if (event.getSource() == b3) {
            codeCalculatorVisual = "3";
            codeCalculator = "3";
        }
        if (event.getSource() == b4) {
            codeCalculatorVisual = "4";
            codeCalculator = "4";
        }
        if (event.getSource() == b5) {
            codeCalculatorVisual = "5";
            codeCalculator = "5";
        }
        if (event.getSource() == b6) {
            codeCalculatorVisual = "6";
            codeCalculator = "6";
        }
        if (event.getSource() == b7) {
            codeCalculatorVisual = "7";
            codeCalculator = "7";
        }
        if (event.getSource() == b8) {
            codeCalculatorVisual = "8";
            codeCalculator = "8";
        }
        if (event.getSource() == b9) {
            codeCalculatorVisual = "9";
            codeCalculator = "9";
        }
        if (event.getSource() == bCos) {
            codeCalculatorVisual = " cos(";
            codeCalculator = " cos(";
        }
        if (event.getSource() == bSen) {
            codeCalculatorVisual = " sen(";
            codeCalculator = " sin(";
        }
        if (event.getSource() == bTan) {
            codeCalculatorVisual = " tan(";
            codeCalculator = " tan(";
        }
        if (event.getSource() == bAcos) {
            codeCalculatorVisual = " acos(";
            codeCalculator = " acos(";
        }
        if (event.getSource() == bAsen) {
            codeCalculatorVisual = " asen(";
            codeCalculator = " asin(";
        }
        if (event.getSource() == bAtan) {
            codeCalculatorVisual = " atan(";
            codeCalculator = " atan(";
        }
        if (event.getSource() == bopenParenthesis) {
            codeCalculatorVisual = " (";
            codeCalculator = " (";
        }
        if (event.getSource() == bcloseParenthesis) {
            codeCalculatorVisual = " )";
            codeCalculator = " )";
            if (watcherLog) {
                //log x = ln(x)/ln(10)
                codeCalculator = ")/log(10) ";
            }
        }
        if (event.getSource() == bCcomma) {
            codeCalculatorVisual = ",";
            codeCalculator = ".";
        }
        if (event.getSource() == bEuler) {
            codeCalculatorVisual = " e";
            codeCalculator = String.valueOf(Math.E);
        }
        if (event.getSource() == bPi) {
            codeCalculatorVisual = "π";
            codeCalculator = String.valueOf(Math.PI);
        }
        if (event.getSource() == bEx) {
            codeCalculatorVisual = "x";
            codeCalculator = "x";
        }
        if (event.getSource() == bDenominator) {
            codeCalculatorVisual = "1/";
            codeCalculator = " 1/";
        }
        if (event.getSource() == bExpoent) {
            codeCalculatorVisual = " ^";
            codeCalculator = "^";
        }
        if (event.getSource() == bSqrt) {
            codeCalculatorVisual = " √(";
            codeCalculator = " sqrt(";
        }
        if (event.getSource() == bLn) {
            codeCalculatorVisual = " ln(";
            codeCalculator = " log(";
        }
        if (event.getSource() == bSqrt3) {
            codeCalculatorVisual = " ∛(";
            codeCalculator = " cbrt(";
        }
        if (event.getSource() == bLog) {
            codeCalculatorVisual = " log(";
            codeCalculator = " log(";
            watcherLog = true;
            //log x = ln(x)/ln(10)
        }
        if (event.getSource() == bMore) {
            codeCalculatorVisual = " + ";
            codeCalculator = " + ";
        }
        if (event.getSource() == bLess) {
            codeCalculatorVisual = " - ";
            codeCalculator = " - ";
        }
        if (event.getSource() == bMultiplied) {
            codeCalculatorVisual = " * ";
            codeCalculator = " * ";
        }
        if (event.getSource() == bDivided) {
            codeCalculatorVisual = " / ";
            codeCalculator = " / ";
        }
        String[] digits = {codeCalculator, codeCalculatorVisual};
        return digits;
    }//fim do método getDigit

    //método para teste
    public static void main(String[] args) {
        JFrame test = new JFrame("test");
        test.setLayout(null);
        test.setSize(500, 500);
        test.setLocationRelativeTo(null);
        PanelDigits pd = new PanelDigits();
        JPanel panelDigits = pd.getPDigits();
        panelDigits.setLocation(0, 0);
        test.add(panelDigits);
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}//fim da classe PanelDigits

