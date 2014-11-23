
/*-
 * Classname:             CalculationBiseccao.java
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

/**
 * Classe responsável pelo calculo da Bisecção
 *
 * @see uni.uri.calculobiseccao.TreatsExpression
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class BisectionModel {

    //funçao
    private String function;
    //intervalo a
    private double a;
    //intervalo b
    private double b;
    //x
    private double x;
    //TOL
    private double tol;
    //numero de interações
    private double n;
    //resultado
    private double result;
    //resultado funçao a para calculo do teorema de bolzano
    private double fa;
    private double fb;
    //contador
    private int count = 0;
    //instancia da claase que faz calculo
    TreatsExpression te = new TreatsExpression();

    /**
     * Contrutor sem parêmtros
     */
    public BisectionModel() {
    }//fim do construtor sem parêmtros

    /**
     * Contrutor CalculationBiseccao
     *
     * @param function função
     * @param a intervalo a
     * @param b intervalo b
     * @param tol TOL
     */
    public BisectionModel(String function, double a, double b, double tol) {
        this.function = function;
        this.a = a;
        this.b = b;
        this.tol = tol;
    }//fim do construtor

    /**
     * Define função
     *
     * @param function função
     */
    public void setFunction(String function) {
        this.function = function;
    }//fim do método setFunction

    /**
     * Define intervalo a
     *
     * @param a intervalo a
     */
    public void setA(double a) {
        this.a = a;
    }//fim do setA

    /**
     * Define intervalo b
     *
     * @param b intervalo b
     */
    public void setB(double b) {
        this.b = b;
    }//fim do método setB

    /**
     * Define tol tolerência
     *
     * @param tol TOL
     */
    public void setTol(double tol) {
        this.tol = tol;
    }//fim do método setTol

    /**
     * Calcula e retorna número de interações
     *
     * @return <code>double</code> número de interações
     */
    public double getN() {
        double log1 = Math.log10(b - a);
        double log2 = Math.log10(tol);
        double log3 = Math.log10(2);
        n = (log1 - log2) / log3;
        return n;
    }//fim do método getN

    /**
     * Calcula e retorna o x
     *
     * @return <code>double</code> x
     */
    public double getX() {
        x = (a + b) / 2;
        return x;
    }//fim do método getX

    /**
     * Calcula e retorna o resultado da função
     *
     * @return <code>double</code> resultado da função em x
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public double getResult() throws UnknownFunctionException, UnparsableExpressionException {
        //chama função que se conecta ao parser
        result = te.calcule(function, x);
        return result;
    }//fim do método getResult
    
    public double calculaF(double entrada) throws UnknownFunctionException, UnparsableExpressionException {
        //chama função que se conecta ao parser
        result = te.calcule(function, entrada);
        return result;
    }//fim do método calculaF

    /**
     * Calcula novos valores para a e b
     */
    public void calculesNewValues() throws UnknownFunctionException, UnparsableExpressionException {
        fa = te.calcule(function, a);
        //se f(x) e f(a) tem sinal diferente a=a b=x
        if ((fa * result) < 0) {
            b = x;
        } else {
            a = x;
        }
    }//fim do método calculesNewValues

    /**
     * Obtêm o valor de a
     *
     * @return <code>Double</code> a
     */
    public double getA() {
        return a;
    }//fim do método getA

    /**
     * Obtêm o valor de b
     *
     * @return <code>Double</code> b
     */
    public double getB() {
        return b;
    }//fim do método getB

    //método main para teste
    public static void main(String[] args) {
        String function = "x^2-4*x+4";
        double tol = 0.01;
        double a = 1.2;
        double b = 2.9;
        double x;
        double n;
        double result = 0;
        double fa;
        double fb;
        TreatsExpression te = new TreatsExpression();
        n = (Math.log10(b - a) - Math.log10(tol)) / Math.log10(2);
        System.out.println("N=" + n);
        try {
            for (int i = 0; i < n; i++) {
                x = (a + b) / 2;
                result = te.calcule(function, x);
                fa = te.calcule(function, a);
                fb = te.calcule(function, b);
                if ((fa * fb) > 0) { System.out.println("Intervalo Não Contém uma Raiz, Contém Multiplas Raízes ou Não Pode Ser Estimado pelo Método da Bissecção!. f(a)*f(b) = " + fa*fb); break; }
                if (result == 0 || x < tol) {
                    n = i;
                }
                System.out.println("i=" + i + " a=" + a + " b=" + b + " x=" + x + " Resultado=" + result);
                //se f(x) e f(a) tem sinal diferente a=a b=x
                if ((fa * result) < 0) {
                    b = x;
                } else {
                    a = x;
                }

            }
        } catch (Exception ex) {
            System.err.println("ERRO");
        }
    }
}//fim da classe BisectionController 

