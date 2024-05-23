package edu.austral.ingsis.math;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResolutionTest {

  BinaryMapper binaryMapper = new BinaryMapper();
  UnaryMapper unaryMapper = new UnaryMapper();

  /** Case 1 + 6 */
  @Test
  public void shouldResolveSimpleFunction1() {

    Function fun =
        new BinaryExpression(
            new Value(1),
            new Value(6),
            binaryMapper.operaciones.get(BinaryOperators.SUM),
            BinaryOperators.SUM);

    final Double result = fun.resolve().getSuccess();
    assertThat(result, equalTo(7d));
  }

  /** Case 12 / 2 */
  @Test
  public void shouldResolveSimpleFunction2() {
    Function fun =
        new BinaryExpression(
            new Value(12),
            new Value(2),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);

    final Double result = fun.resolve().getSuccess();

    assertThat(result, equalTo(6d));
  }

  /** Case (9 / 2) * 3 */
  @Test
  public void shouldResolveSimpleFunction3() {

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
    final Double result = fun.resolve().getSuccess();

    assertThat(result, equalTo(13.5d));
  }

  /** Case (27 / 6) ^ 2 */
  @Test
  public void shouldResolveSimpleFunction4() {
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

    final Double result = fun.resolve().getSuccess();

    assertThat(result, equalTo(20.25d));
  }

  /** Case 36 ^ (1/2) */
  @Test
  public void shouldResolveSimpleFunction5() {
    Function mitad =
        new BinaryExpression(
            new Value(1),
            new Value(2),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);
    Function fun =
        new BinaryExpression(
            new Value(36),
            mitad,
            binaryMapper.operaciones.get(BinaryOperators.POWER),
            BinaryOperators.POWER);
    final Double result = fun.resolve().getSuccess();
    assertThat(result, equalTo(6d));
  }

  /** Case |136| */
  @Test
  public void shouldResolveSimpleFunction6() {

    Function fun =
        new UnaryExpression(
            new Value(136), unaryMapper.operaciones.get(UnaryOperators.ABS), UnaryOperators.ABS);
    final Double result = fun.resolve().getSuccess();

    assertThat(result, equalTo(136d));
  }

  /** Case |-136| */
  @Test
  public void shouldResolveSimpleFunction7() {
    Function fun =
        new UnaryExpression(
            new Value(-136), unaryMapper.operaciones.get(UnaryOperators.ABS), UnaryOperators.ABS);
    final Double result = fun.resolve().getSuccess();

    assertThat(result, equalTo(136d));
  }

  /** Case (5 - 5) * 8 */
  @Test
  public void shouldResolveSimpleFunction8() {
    Function leftFun =
        new BinaryExpression(
            new Value(5),
            new Value(5),
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
    final Double result = fun.resolve().getSuccess();
    assertThat(result, equalTo(0d));
  }
}
