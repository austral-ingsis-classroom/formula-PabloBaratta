package edu.austral.ingsis.math;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import org.junit.Test;

public class ResolutionWithVariablesTest {

  BinaryMapper binaryMapper = new BinaryMapper();
  UnaryMapper unaryMapper = new UnaryMapper();

  /** Case 1 + x where x = 3 */
  @Test
  public void shouldResolveFunction1() {
    Function fun =
        new BinaryExpression(
            new Value(1),
            new Variable("x"),
            binaryMapper.operaciones.get(BinaryOperators.SUM),
            BinaryOperators.SUM);

    Try<Double, Exception> resolve = fun.resolve(Map.of("x", 3.0));
    assertThat(resolve.getSuccess(), equalTo(4d));
  }

  /** Case 12 / div where div = 4 */
  @Test
  public void shouldResolveFunction2() {
    Function fun =
        new BinaryExpression(
            new Value(12),
            new Variable("div"),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);

    final Double result = fun.resolve(Map.of("div", 4.0)).getSuccess();

    assertThat(result, equalTo(3d));
  }

  /** Case (9 / x) * y where x = 3 and y = 4 */
  @Test
  public void shouldResolveFunction3() {
    Function leftFun =
        new BinaryExpression(
            new Value(9),
            new Variable("x"),
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
            new Variable("y"),
            binaryMapper.operaciones.get(BinaryOperators.MULTIPLICATION),
            BinaryOperators.MULTIPLICATION);
    final Double result = fun.resolve(Map.of("x", 3.0, "y", 4.0)).getSuccess();

    assertThat(result, equalTo(12d));
  }

  /** Case (27 / a) ^ b where a = 9 and b = 3 */
  @Test
  public void shouldResolveFunction4() {
    Function leftFun =
        new BinaryExpression(
            new Value(27),
            new Variable("a"),
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
            new Variable("b"),
            binaryMapper.operaciones.get(BinaryOperators.POWER),
            BinaryOperators.POWER);
    final Double result = fun.resolve(Map.of("a", 9.0, "b", 3.0)).getSuccess();

    assertThat(result, equalTo(27d));
  }

  /** Case z ^ (1/2) where z = 36 */
  @Test
  public void shouldResolveFunction5() {
    Function rightFun =
        new BinaryExpression(
            new Value(1),
            new Value(2),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);
    Function parenthesis =
        new UnaryExpression(
            rightFun,
            unaryMapper.operaciones.get(UnaryOperators.PARENTHESIS),
            UnaryOperators.PARENTHESIS);
    Function fun =
        new BinaryExpression(
            new Variable("z"),
            parenthesis,
            binaryMapper.operaciones.get(BinaryOperators.POWER),
            BinaryOperators.POWER);
    final Double result = fun.resolve(Map.of("z", 36.0)).getSuccess();

    assertThat(result, equalTo(6d));
  }

  /** Case |value| - 8 where value = 8 */
  @Test
  public void shouldResolveFunction6() {
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
    final Double result = fun.resolve(Map.of("value", 8.0)).getSuccess();

    assertThat(result, equalTo(0d));
  }

  /** Case |value| - 8 where value = 8 */
  @Test
  public void shouldResolveFunction7() {
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
    final Double result = fun.resolve(Map.of("value", 8.0)).getSuccess();

    assertThat(result, equalTo(0d));
  }

  /** Case (5 - i) * 8 where i = 2 */
  @Test
  public void shouldResolveFunction8() {
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
    final Double result = fun.resolve(Map.of("i", 2.0)).getSuccess();

    assertThat(result, equalTo(24d));
  }
}
