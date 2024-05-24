package edu.austral.ingsis.math;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InvalidOperationsTest {
  BinaryMapper binaryMapper = new BinaryMapper();

  @Test
  public void negativeSquareRootTest() {
    BinaryExpression binaryExpression =
        new BinaryExpression(
            new Value(-1),
            new Value(2),
            binaryMapper.operaciones.get(BinaryOperators.POWER),
            BinaryOperators.POWER);
    Try<Double, Exception> resolve = binaryExpression.resolve();
    assertTrue(resolve.isFailure());
  }

  @Test
  public void divisionByZero() {
    BinaryExpression binaryExpression =
        new BinaryExpression(
            new Value(-1),
            new Value(0),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);
    Try<Double, Exception> resolve = binaryExpression.resolve();
    assertTrue(resolve.isFailure());
  }

  @Test
  public void invalidInUnary() {
    BinaryExpression binaryExpression =
        new BinaryExpression(
            new Value(-1),
            new Value(0),
            binaryMapper.operaciones.get(BinaryOperators.DIVSION),
            BinaryOperators.DIVSION);
    UnaryExpression unaryExpression =
        new UnaryExpression(binaryExpression, (x) -> x, UnaryOperators.PARENTHESIS);
    Try<Double, Exception> resolve = unaryExpression.resolve();
    assertTrue(resolve.isFailure());
  }
}
