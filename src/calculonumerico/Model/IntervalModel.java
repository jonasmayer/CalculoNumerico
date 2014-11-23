
/*-
 * Classname:             CalculationInterval.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  02/10/2012 - 19:52:23
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
package calculonumerico.Model;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável pelo calculo da Bisecção
 *
 * @see uni.uri.calculobiseccao.TreatsExpression
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class IntervalModel {

    //funçao
    private String function;
    //numero de interações
    private double n;
    //resultado
    private double result;
    //resultado funçao a para calculo do teorema de bolzano
    private double fa;
    //contador
    private int count = 0;
    //instancia da claase que faz calculo
    TreatsExpression te = new TreatsExpression();
    //lista com intervalo superior
    List interval;
    //lista com raizes
    List root;

    /**
     * Contrutor vazio CalculationInterval
     *
     * @throws Exceção no caso de número inválido
     */
    public IntervalModel() {
    }//fim do construtor

    /**
     * Construtor de CalculationInterval
     *
     * @param function função
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public IntervalModel(String function) throws UnknownFunctionException, UnparsableExpressionException {

        //atualiza função
        this.function = function;

        //limite inferior de calculos
        int x = -1000;

        //resultado de intervalo inferior
        double r1 = 0;
        //resultado de intervalo superior
        double r2;

        //inicializa listas
        interval = new ArrayList();
        root = new ArrayList();

        //sentinela de tempo inicial
        boolean firstTime = true;

        //define  limite superior como 100
        //passa posiveis intervalos
        while (x < 1000) {
            //calcula intervalo superior
            r2 = te.calcule(function, x);

            //impede que o primeiro tempo seja definido como raiz por engano
            if (firstTime) {
                r1 = te.calcule(function, x - 1);
            }
            //se não for primeiro intervalo
            if (!firstTime) {
                //se for raiz adiciona a lista de raizes
                if (r2 == 0) {
                    root.add(x);
                } else if ((r1 * r2) < 0) {
                    //se for intervalo válido adiciona a lista de intervalos válidos
                    interval.add(x);
                }
            }

            //desativa sentinela de tempo incial
            firstTime = false;
            //passa para o próximo resultado
            r1 = r2;
            //incrementa possivel intervalo
            x++;
        }
    }//fim do construtor

    /**
     * Retorna lista com intervalos
     *
     * @return <code>List</code> Intervalos
     */
    public List getList() {

        //lista com intervalos
        List list = new ArrayList();

        //une listas de intervalos
        int condition;
        if (interval.size() > root.size()) {
            condition = interval.size();
        } else {
            condition = root.size();
        }

        for (int i = 0; i <= condition; i++) {

            //define intervalo da raiz como sedo raiz-1 e raiz+1
            if (i < root.size()) {
                int i1 = ((Integer) root.get(i)) - 1;
                int i2 = ((Integer) root.get(i)) + 1;
                list.add(i1 + " " + i2);

            }
            //define intervalo com intervalo superior-1 e intervalo superior
            if (i < interval.size()) {
                int i1 = ((Integer) interval.get(i)) - 1;
                int i2 = ((Integer) interval.get(i));
                list.add(i1 + " " + i2);

            }
        }
        // ordena lista
        Collections.sort(list);
        return list;
    }//fim do método getList
}//fim da classe IntervalController 
