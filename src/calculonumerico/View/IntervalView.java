
/*-
 * Classname:             FCalculatorBisseccao.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  01/10/2012 - 15:48:12
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
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Visão da Calculadora de intervalos
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class IntervalView extends GenericCalculatorView {

    //list com os intervalos
    private JList lInterval;
    private JButton bBisseccao;
    private JButton bNewton;
    private JScrollPane scrollList;
    private JLabel intervalLabrl;
    //intervalos
    private int x1;
    private int x2;

    /**
     * Construtor sem parâmetros
     */
    public IntervalView() {
        super();
        createView();
    }//fim do consttrutor

    /**
     * Cria visão
     */
    private void createView() {

        //define título para o frame
        fPrincipal.setTitle("Calculo dos Intervalos");
        //inicializa componentes
        lInterval = new JList();
        intervalLabrl = new JLabel("Intervalos:");
        bBisseccao = new JButton("Bissecção");
        bNewton = new JButton("Newton");
        scrollList = new JScrollPane(lInterval);

        //define tamanho e localização 
        intervalLabrl.setBounds(475, 120, 120, 30);
        scrollList.setBounds(475, 150, 120, 150);
        bBisseccao.setBounds(430, 450, 110, 25);
        bNewton.setBounds(550, 450, 110, 25);

        //define cor do texto
        intervalLabrl.setBackground(Color.BLACK);

        //adiciona componentes
        fPrincipal.add(intervalLabrl);
        fPrincipal.add(scrollList);
        fPrincipal.add(bBisseccao);
        fPrincipal.add(bNewton);
    }//fim do construtor

    /**
     * Define ouvinte de evento para botão bissecção
     *
     * @param actionListener ouvinte
     */
    public void setActionListenerBisseccao(ActionListener actionListener) {
        bBisseccao.addActionListener(actionListener);//fim do evento
    }//fim do método setActionListenerBisseccao

    /**
     * Define ouvinte de evento para botão newton
     *
     * @param actionListener ouvinte
     */
    public void setActionListenerNewton(ActionListener actionListener) {
        bNewton.addActionListener(actionListener);//fim do evento
    }//fim do método setActionListenerNewton

    /**
     * Obtêm indice do intervalo selecionado
     *
     * @return <code>Integer</code> indice selecionado
     */
    public int getSelectedListIndex() {
        return lInterval.getSelectedIndex();
    }//fim do método getSelectedListIndex

    /**
     * Obtêm intervalo selecionado
     *
     * @return <code>String[]</code> intervalo
     */
    public String[] getSelectedListValues() {
        if (lInterval.getSelectedValue() != null) {
            String g = (String) lInterval.getSelectedValue();
            return g.split(" ");
        } else {
            return null;
        }
    }//fim do método getSelectedListValues

    /**
     * Define intervalos encontrados
     *
     * @param tempList intervalos
     */
    public void setIntervals(Object[] tempList) {
        lInterval.setListData(tempList);
    }//fim do método setIntervals
}//fim da classe IntervalView 
