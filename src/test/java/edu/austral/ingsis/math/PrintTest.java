package edu.austral.ingsis.math;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PrintTest {
  BinaryMapper binaryMapper = new BinaryMapper();
  UnaryMapper unaryMapper = new UnaryMapper();

  /** Case 1 + 6 */
  @Test
  public void shouldPrintFunction1() {
    final String expected = "1 + 6";
    Function fun =
        new BinaryExpression(
            new Value(1),
            new Value(6),
            binaryMapper.operaciones.get(BinaryOperators.SUM),
            BinaryOperators.SUM);
    final String result = fun.toString();

    assertThat(result, equalTo(expected));
  }

  /** Case 12 / 2 */
  @Test
  public void shouldPrintFunction2() {
    final String expected = "12 / 2";

    Function fun =
        new BinaryExpression(
            new Value(12),
            new Value(2),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);

    final String result = fun.toString();

    assertThat(result, equalTo(expected));
  }

  /** Case (9 / 2) * 3 */
  @Test
  public void shouldPrintFunction3() {
    final String expected = "(9 / 2) * 3";

    Function leftFun =
        new BinaryExpression(
            new Value(9),
            new Value(2),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);
    Function parenthesis =
        new UnaryExpression(
            leftFun,
            unaryMapper.operaciones.get(UnaryOperators.PARENTHESIS),
            UnaryOperators.PARENTHESIS);
    Function fun =
        new BinaryExpression(
            parenthesis,
            new Value(3),
            binaryMapper.operaciones.get(BinaryOperators.MULTIPLICATION),
            BinaryOperators.MULTIPLICATION);
    final String result = fun.toString();

    assertThat(result, equalTo(expected));
  }

  /** Case (27 / 6) ^ 2 */
  @Test
  public void shouldPrintFunction4() {
    final String expected = "(27 / 6) ^ 2";

    Function leftFun =
        new BinaryExpression(
            new Value(27),
            new Value(6),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);
    Function parenthesis =
        new UnaryExpression(
            leftFun,
            unaryMapper.operaciones.get(UnaryOperators.PARENTHESIS),
            UnaryOperators.PARENTHESIS);
    Function fun =
        new BinaryExpression(
            parenthesis,
            new Value(2),
            binaryMapper.operaciones.get(BinaryOperators.POWER),
            BinaryOperators.POWER);

    final String result = fun.toString();

    assertThat(result, equalTo(expected));
  }

  /** Case |value| - 8 */
  @Test
  public void shouldPrintFunction6() {
    final String expected = "|value| - 8";
    Function left =
        new UnaryExpression(
            new Variable("value"),
            unaryMapper.operaciones.get(UnaryOperators.ABS),
            UnaryOperators.ABS);
    Function fun =
        new BinaryExpression(
            left,
            new Value(8),
            binaryMapper.operaciones.get(BinaryOperators.SUBSTRACTION),
            BinaryOperators.SUBSTRACTION);

    final String result = fun.toString();

    assertThat(result, equalTo(expected));
  }

  /** Case |value| - 8 */
  @Test
  public void shouldPrintFunction7() {
    final String expected = "|value| - 8";
    Function left =
        new UnaryExpression(
            new Variable("value"),
            unaryMapper.operaciones.get(UnaryOperators.ABS),
            UnaryOperators.ABS);
    Function fun =
        new BinaryExpression(
            left,
            new Value(8),
            binaryMapper.operaciones.get(BinaryOperators.SUBSTRACTION),
            BinaryOperators.SUBSTRACTION);

    final String result = fun.toString();
    assertThat(result, equalTo(expected));
  }

  /** Case (5 - i) * 8 */
  @Test
  public void shouldPrintFunction8() {
    final String expected = "(5 - i) * 8";

    Function leftFun =
        new BinaryExpression(
            new Value(5),
            new Variable("i"),
            binaryMapper.operaciones.get(BinaryOperators.SUBSTRACTION),
            BinaryOperators.SUBSTRACTION);
    Function parenthesis =
        new UnaryExpression(
            leftFun,
            unaryMapper.operaciones.get(UnaryOperators.PARENTHESIS),
            UnaryOperators.PARENTHESIS);
    Function fun =
        new BinaryExpression(
            parenthesis,
            new Value(8),
            binaryMapper.operaciones.get(BinaryOperators.MULTIPLICATION),
            BinaryOperators.MULTIPLICATION);
    final String result = fun.toString();

    assertThat(result, equalTo(expected));
  }
}
