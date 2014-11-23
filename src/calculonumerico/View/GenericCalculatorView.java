/*-
 * Classname:             GenericCalculatorView.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  01/10/2012 - 15:48:55
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe abstrata com métodos genéricos das visões das calculadoras
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public abstract class GenericCalculatorView {

    //lista com a função lógica
    public List<String> fxList;
    //lista com a visão da função que será apresentada ao usuário
    public List<String> visualFxList;
    //contador da lista com a função lógica
    public int countList = 0;
    //contador da lista com a função visual
    public int countListVisual = 0;
    //funçao que será passada para o parser
    private String fx;
    //GUI
    //frame principal
    public JFrame fPrincipal;
    //panel com digitos
    private JPanel pDigits;
    //label função
    private JLabel lFxE;
    //textfields
    //campo da expressão
    private JTextField tExpression;
    //campo do intervalo a
    public JTextField tInterval1;
    //campo do intervalo b
    public JTextField tInterval2;
    //sentinelas
    //sentinela do contador serve para saber quando o count está +1
    private boolean watcherCount = true;
    //sentinela de erase sinaliza quando ouve exclusão
    private boolean watcherErase = false;
    //sentinela para sinalizar quando a função está vazia
    private boolean isZeroAgain = false;
    //define quando está no primeiro digito
    private boolean first = true;
    //variavel para corrigir erro com adicionar apagar adicionar
    private int countOperation = 0;
    //botão para retorno
    private JButton bReturn;
    //painel de controle
    private PanelDigits pd = new PanelDigits();

    /**
     * Construtor sem parâmetros
     */
    public GenericCalculatorView() {

        //inicializa listas da função
        fxList = new ArrayList();
        visualFxList = new ArrayList();
        fPrincipal = new JFrame();

        //inicializa componentes
        bReturn = new JButton("Retornar");
        pDigits = pd.getPDigits();
        tExpression = new JTextField();
        tExpression.setEditable(false);
        tInterval1 = new JTextField();
        lFxE = new JLabel("f(x)=");

        //define layout
        fPrincipal.setLayout(null);

        //define tamanho e localização dos componentes
        fPrincipal.setBounds(0, 0, 700, 550);
        pDigits.setLocation(10, 50);
        bReturn.setBounds(550, 30, 100, 20);
        lFxE.setBounds(15, 20, 50, 30);
        tExpression.setBounds(50, 20, 400, 30);
        Font fg = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        lFxE.setFont(fg);
        tExpression.setFont(fg);

        //adiciona componentes a janela principal
        fPrincipal.add(pDigits);
        fPrincipal.add(bReturn);
        fPrincipal.add(lFxE);
        fPrincipal.add(tExpression);

        //condifurações finais
        fPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fPrincipal.setVisible(true);
    }//fim do construtor sem parâmetros

    /**
     * Adiciona valor ao função
     *
     * @param expression trecho com operador ou digito
     */
    public void addExpression(String expression) {
        //incrementa contador de operações
        countOperation++;
        //adiciona o operador a expressão
        fxList.add(expression);
        //incremente contador da list
        countList++;
        //ativa sentinela do contador
        watcherCount = true;

        //se ouve uma exclusão os contadores são incrementados e o rwxtfield é atualizado
        if (watcherErase) {
            countList++;
            countListVisual++;
            watcherErase = false;
            addFX(calculeVisual());
        }
    }//fim do método addExpression

    /**
     * Adiciona um valor a função visual
     *
     * @param expression trecho visual de expressão
     */
    public void addVisualExpression(String expression) {
        //define a função como não vazia
        first = false;
        //adiciona valor a list
        visualFxList.add(expression);
        //incrementa contador da lista da função visual
        countListVisual++;
        //atualiza textfielf
        addFX(calculeVisual());
    }//fim do método addVisualExpression

    /**
     * Apaga ultimo valor ou operador da função
     */
    public void eraseExpression() {
        //incrementa contador de operações
        countOperation++;
        //apaga apenas se a lista não estiver vazia
        if (!first) {
            //se a sentinela do contador estiver ativa decrementa
            if (watcherCount) {
                countList--;
                watcherCount = false;
            }
            //remove ultimo elemento da lista
            fxList.remove(countList);

            //se o contador for maior que 0 decrementa
            if (countList > 0) {
                countList--;
            }
            //ativa sentinela de exclusão
            watcherErase = true;

            //se o lista estiver vazia ativa sentinela
            if (countList == 0) {
                //define como vazia
                first = true;
                isZeroAgain = true;

                //impede erro adicionar apagar adicionar
                if (countOperation == 2) {
                    watcherErase = false;
                }
                countOperation = 0;
            }
        } else {
            //limpa dados
            eraseCE();
            watcherErase = false;

        }
    }//fim do método eraseExpression

    /**
     * Apaga último valor ou operador da função visual
     */
    public void eraseVisualExpression() {
        //se a lista não estiver vazia apaga
        if (!first) {
            //se a sentinela do contador estiver ativa decrementa o contador
            if (watcherCount) {
                countListVisual--;
            }

            //remove ultimo elemento da lista visual
            visualFxList.remove(countListVisual);

            //atualiza visor da claculadora
            addFX(calculeVisual());
            watcherErase = true;
            //se o contador for maior que zero decrementa
            if (countListVisual > 0) {
                countListVisual--;
            }

        }
    }//fim do método eraseVisualExpression

    /**
     * Transforma a lista de função em String
     *
     * @return <code>String</code> com função
     */
    public String calcule() {
        //se a sentinela de contador estiver desativada incrementa o contador
        if (!watcherCount) {
            countList++;
        }
        //para armazenamento temporário
        StringBuilder temp = new StringBuilder();

        //transforma lista em string
        for (int i = 0; i < fxList.size(); i++) {
            temp.append(fxList.get(i));
        }
        //retorna string com a função
        return temp.toString();
    }//fim do método calcule

    /**
     * Calcula a expressão visual para o textfield
     *
     * @return <code>String</code> express]ao visual
     */
    public String calculeVisual() {
        //para armazenamento temporário
        StringBuilder temp = new StringBuilder();
        //transforma lista em string
        for (int i = 0; i < countListVisual; i++) {
            temp.append(visualFxList.get(i));
        }
        //retorna string com a função
        return temp.toString();
    }//fim do método calculeVisual

    /**
     * Atualiza textfield
     *
     * @param exp função
     */
    public void addFX(String exp) {
        tExpression.setText(exp);
    }//fim do método addFX

    /**
     * Zera calculadora
     */
    public void eraseCE() {
        fxList.clear();
        visualFxList.clear();
        countList = 0;
        countListVisual = 0;
        tExpression.setText("");
        countOperation = 0;
        first = true;
        watcherCount = true;
        watcherErase = false;
        isZeroAgain = false;
    }//fim do método eraseCE

    /**
     * Método para definir intervalos e função apartir da calculadora de
     * intervalos
     *
     * @param list lista com função
     * @param visual função visual
     * @param count contador
     * @param countVisual contador visual
     * @param x intervalo x
     * @param y intervalo y
     */
    public void setInterval(List list, List visual, int count, int countVisual, int x, int y) {
        String tempIg = "";
        for (int i = 0; i < countVisual; i++) {
            tempIg = tempIg + visual.get(i);
        }
        //define novos valores
        tExpression.setText(tempIg);
        fxList = list;
        visualFxList = visual;
        countList = count;
        countListVisual = countVisual;
        tInterval1.setText("" + x);
        if (tInterval2 != null) {
            tInterval2.setText("" + y);
        }
    }//fim do método setInterval

    /**
     * Define evento genérico para teclas do painel
     */
    public void setPanelDigitsListeners() {
        pd.setGeneticActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] digits = pd.getDigit(e);
                addVisualExpression(digits[1]);
                addExpression(digits[0]);
            }
        });
    }//fim do método setPanelDigitsListeners

    /**
     * Define evento para o botão CE
     */
    public void setCEActionListener() {
        pd.setCEActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraseCE();
            }
        });
    }//fim do método setCEActionListener

    /**
     * Define evento para o botão apagar
     */
    public void setEraseActionListener() {
        pd.setEraseActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraseVisualExpression();
                eraseExpression();
            }
        });
    }//fim do método setEraseActionListener

    /**
     * Define evento para o botão =
     *
     * @param listener
     */
    public void setResultActionListener(ActionListener listener) {
        pd.setResultListenerActionListener(listener);
    }//fim do método setResultActionListener

    /**
     * Define evento para botão retornar
     *
     * @param actionListener
     */
    public void setReturnActionListener(ActionListener actionListener) {
        bReturn.addActionListener(actionListener);
    }//fim do método setReturnActionListener

    /**
     * Fecha o frame
     */
    public void close() {
        fPrincipal.dispose();
    }//fim do método close

    /**
     * Obtêm lista da função
     *
     * @return <code>List</code> função
     */
    public List getFxList() {
        return fxList;
    }//fim do método getFxList

    /**
     * Obtêm lista visual da função
     *
     * @return <code>List</code> função visual
     */
    public List getVisualFxList() {
        return visualFxList;
    }//fim do método getVisualFxList

    /**
     * Obtêm contador da lista de função
     *
     * @return <code>Integer</code> contador
     */
    public int getCountList() {
        return countList;
    }//fim do método getCountList

    /**
     * Obtêm contador da lista de função visual
     *
     * @return <code>Integer</code> contador
     */
    public int getCountListVisual() {
        return countListVisual;
    }//fim do método getCountListVisual
}//fim da classe GenericCalculatorView 

