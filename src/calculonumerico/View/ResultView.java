/*-
 * Classname:             ResultView.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  08/05/2013 - 13:38:49
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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Visão com o resultado
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class ResultView {

    //frame principal
    private JFrame fResult;
    //tabela
    private JTable tableResult;
    //modelo padrão da tabela
    private DefaultTableModel model;

    /**
     * Contrutor com dados da tabela
     *
     * @param list lista com linhas
     */
    public ResultView(List<Object[]> list) {

        //cria visao
        createView();

        //adiciona colunas ao modelo
        model.addColumn("i");
        model.addColumn("x");

        //popula tabela
        for (int i = 0; i < list.size(); i++) {
            model.addRow(list.get(i));
        }
    }//fim do construtor

    /**
     * Cria visão
     */
    private void createView() {

        //inicializa componentes
        fResult = new JFrame("Resultado");

        //define layout e visibilidade
        fResult.setLayout(null);
        fResult.setVisible(true);

        //define tamanho e possição
        fResult.setBounds(700, 0, 400, 550);

        //cria modelo para a tabela  
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        //define modelo
        tableResult = new JTable(model);

        //cria Scroll
        JScrollPane scrollTable = new JScrollPane(tableResult);
        scrollTable.setBounds(0, 0, 390, 540);
        scrollTable.setHorizontalScrollBar(new JScrollBar(0));

        //adiciona scroll ao frame
        fResult.add(scrollTable);

        //define evento para fechamento do frame
        fResult.addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                fResult.dispose();
            }
        });

        //define o tipo de saída
        fResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//fim do método createView

    /**
     * Fecha o frame
     */
    public void close() {
        fResult.dispose();
    }//fim do método close
}//fim da classe ResultView 
