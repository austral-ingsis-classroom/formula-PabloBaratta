package edu.austral.ingsis.math;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;

import java.util.List;
import org.junit.Test;

public class ListVariablesTest {

  BinaryMapper binaryMapper = new BinaryMapper();
  UnaryMapper unaryMapper = new UnaryMapper();

  /** Case 1 + 6 */
  @Test
  public void shouldListVariablesFunction1() {
    Function fun =
        new BinaryExpression(
            new Value(1),
            new Value(6),
            binaryMapper.operaciones.get(BinaryOperators.SUM),
            BinaryOperators.SUM);
    final List<String> result = fun.parameteres();
    assertThat(result, empty());
  }

  /** Case 12 / div */
  @Test
  public void shouldListVariablesFunction2() {
    Function fun =
        new BinaryExpression(
            new Value(12),
            new Variable("div"),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);
    final List<String> result = fun.parameteres();
    assertThat(result, containsInAnyOrder("div"));
  }

  /** Case (9 / x) * y */
  @Test
  public void shouldListVariablesFunction3() {
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
    final List<String> result = fun.parameteres();
    assertThat(result, containsInAnyOrder("x", "y"));
  }

  /** Case (27 / a) ^ b */
  @Test
  public void shouldListVariablesFunction4() {
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
    final List<String> result = fun.parameteres();

    assertThat(result, containsInAnyOrder("a", "b"));
  }

  /** Case z ^ (1/2) */
  @Test
  public void shouldListVariablesFunction5() {
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
    final List<String> result = fun.parameteres();
    assertThat(result, containsInAnyOrder("z"));
  }

  /** Case |value| - 8 */
  @Test
  public void shouldListVariablesFunction6() {
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
    final List<String> result = fun.parameteres();
    assertThat(result, containsInAnyOrder("value"));
  }

  /** Case |value| - 8 */
  @Test
  public void shouldListVariablesFunction7() {
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
    final List<String> result = fun.parameteres();
    assertThat(result, containsInAnyOrder("value"));
  }

  /** Case (5 - i) * 8 */
  @Test
  public void shouldListVariablesFunction8() {
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
    final List<String> result = fun.parameteres();
    assertThat(result, containsInAnyOrder("i"));
  }
}
