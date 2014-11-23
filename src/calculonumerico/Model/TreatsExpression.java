
/*-
 * Classname:             TreatsExpression.java
 *
 * Version information:   0.72 (beta)
 *
 * Date:                  01/10/2012 - 15:53:22
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

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 * Classe responsável por interagir com o parser
 *
 * @see de.congrace.exp4j.Calculable
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class TreatsExpression {

    /**
     * Calcula o resultado da função
     *
     * @param expression função
     * @param x x da função
     * @return <code>Douuble</code> resultado
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public double calcule(String expression, double x) throws UnknownFunctionException, UnparsableExpressionException {
        double varX = x;
        Calculable calc = new ExpressionBuilder(expression).withVariable("x", varX).build();
        double result = calc.calculate();
        return result;
    }//fim do método calcule
}//fim da classe TreatsExpression
