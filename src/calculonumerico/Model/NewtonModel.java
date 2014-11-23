/*-
 * Classname:             NewtonController.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  19/10/2012 - 10:54:12
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

/**
 * Modelo da calculadora do método de Newton
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class NewtonModel {

    //valor da função
    private double fx1;
    //valor da função muleta para calculo de derivada
    private double fxa;
    //variavel para calculo de derivada
    private double ax = 0.0000001;
    //intervalo seguinte
    private double x2 = 0;
    //valor da derivada
    private double derv;
    //contador
    private int count = 0;
    //sentinela para pr[oximo elemento
    private boolean sent = false;
    //classe do parser que faz calculo
    private TreatsExpression te;
    //função
    private String exp;
    //valor inicial
    private double x = 0;
    //TOL
    private double tol;

    /**
     * Construtor sem parâmetros
     *
     * @param exp função
     * @param x intervalo superior
     * @param tol TOL
     */
    public NewtonModel(String exp, double x, double tol) {
        te = new TreatsExpression();
        this.exp = exp;
        this.x = x;
        this.tol = tol;
    }//fim do construtor sem parâmetros

    /**
     * Faz o calculo do método de Newton
     *
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public void calcule() throws UnknownFunctionException, UnparsableExpressionException {

        fx1 = te.calcule(exp, x);
        //função para calulo de muleta de derivada
        fxa = te.calcule(exp, (x + ax));
        //derivada = (f(x+0.0000001)-f(x)) /0.0000001
        derv = (fxa - fx1) / ax;
        //próximo valor = x - (f(x)/derivada f(x))
        x2 = x - (fx1 / derv);
    }//fim do método calcule

    /**
     * Prossegue para o próximo calculo
     */
    public void next() {
        //testa consições de proguesso
        if (Math.abs(x - x2) <= tol) {
            sent = true;
        }
        if (Math.abs(fx1) < tol) {
            sent = true;
        }
        if (count > 100) {
            sent = true;
        }
        if (sent == false) {
            count++;
            x = x2;
        }
    }//fim do método next

    /**
     * Obtêm o valor do contador
     *
     * @return <code>Integer</code> valor do contador
     */
    public int getCount() {
        return count;
    }//fim do método getCount

    /**
     * Obtêm valor do x atual
     *
     * @return <code>Double</code> valor do ler
     */
    public double getX() {
        return x;
    }//fim do método getX

    /**
     * Obtêm valor da sentinela
     *
     * @return <code>Boolean</code> true = fim e <code>Boolean</code> false =
     * continuar
     */
    public boolean getSent() {
        return sent;
    }//fim do método getSent

    //método para teste
    public static void main(String args[]) {
        //funçao
        String exp = "x^2 - 7";
        //x
        double x = 3;
        //TOL
        double tol = 0.00000001;
        double fx;
        double fxa;
        double ax = 0.0000001;
        double x2 = 0;
        double derv;
        int count = 0;
        boolean sent = false;
        TreatsExpression te = new TreatsExpression();
        try {
            while (!sent) {
                fx = te.calcule(exp, x);
                fxa = te.calcule(exp, (x + ax));
                //derivada
                derv = (fxa - fx) / ax;
                //x2
                x2 = x - (fx / derv);

                if (Math.abs(x - x2) <= tol) {
                    sent = true;
                }
                if (Math.abs(fx) < tol) {
                    sent = true;
                }
                if (count > 100) {
                    sent = true;
                }
                if (sent == false) {
                    count++;
                    x = x2;
                }
            }
        } catch (Exception ex) {
            System.err.println("ERRO" + ex);
        }
    }
}//fim da classe NewtonController 

