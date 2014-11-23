/*-
 * Classname:             NewtonView.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  01/10/2012 - 15:58:12
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

import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Visão da calculadora do método ne Newton
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class NewtonView extends GenericCalculatorView {

    //label intervalo
    private JLabel lInterval;
    //label tol
    private JLabel lTOL;
    //textfields
    //campo da tolerencia
    private JTextField tTOL;

    /**
     * Construtor sem paâmetros
     */
    public NewtonView() {
        super();
        createView();
    }//fim do método

    /**
     * Cria visão
     */
    private void createView() {
        //inicializa cpmponentes
        fPrincipal.setTitle("Calculo Newton");
        lInterval = new JLabel("Xº");
        lTOL = new JLabel("TOL");
        tTOL = new JTextField("0.01");

        //define tamanho e localização 
        lInterval.setBounds(420, 70, 80, 25);
        lTOL.setBounds(420, 110, 80, 25);
        tInterval1.setBounds(520, 70, 50, 25);
        tTOL.setBounds(520, 110, 80, 25);

        //adicionar
        fPrincipal.add(lInterval);
        fPrincipal.add(lTOL);
        fPrincipal.add(tInterval1);
        fPrincipal.add(tTOL);

        //recursos visuais 
        Font fg = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        lInterval.setFont(fg);
        lTOL.setFont(fg);
        tInterval1.setFont(fg);
        tTOL.setFont(fg);
    }//fim do método createView

    /**
     * Define lista com função
     *
     * @param fxList lista com função
     */
    public void setFxList(List fxList) {
        this.fxList = fxList;
    }//fim do método setFxList

    /**
     * Define lista com o visual da função
     *
     * @param visualFxList função visual
     */
    public void setVisualFxList(List visualFxList) {
        this.visualFxList = visualFxList;
        addFX(calculeVisual());
    }//fim do método setVisualFxList

    /**
     * Define contador da lista com a função
     *
     * @param countList contador da lista com a função
     */
    public void setCountList(int countList) {
        this.countList = countList;
    }//fim do método setCountList

    /**
     * Define o contador de lista coma função visual e atualiza o frame se
     * possível
     *
     * @param countListVisual contador da lista com função visual
     */
    public void setCountListVisual(int countListVisual) {
        this.countListVisual = countListVisual;
        if (visualFxList.size() <= countListVisual) {
            addFX(calculeVisual());
        }
    }//fim do método setCountListVisual

    /**
     * Define o intervalo superior
     *
     * @param x1
     */
    public void setA(int x1) {
        tInterval1.setText("" + x1);
    }//fim do método setA

    /**
     * Obtêm o intervalo
     *
     * @return <code>String</code>
     */
    public String getInterval1() {
        return tInterval1.getText();
    }//fim do método String

    /**
     * Obtêm o TOL
     *
     * @return <code>String</code> TOL
     */
    public String getTOL() {
        return tTOL.getText();
    }//fim do método getTOL
}//fim da classe NewtonView 
