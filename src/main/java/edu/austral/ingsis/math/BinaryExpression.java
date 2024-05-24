package edu.austral.ingsis.math;

import java.util.*;
import java.util.function.BinaryOperator;

public class BinaryExpression implements Function {

  private final Function left;
  private final Function right;
  private final BinaryOperator<Double> op;
  private final String opCode;

  public BinaryExpression(
      Function left, Function right, BinaryOperator<Double> operator, BinaryOperators opCode) {
    this.left = left;
    this.right = right;
    this.op = operator;
    this.opCode = opCode.toString();
  }

  @Override
  public Try<Double, Exception> resolve() {
    return resolve(Map.of());
  }

  @Override
  public Try<Double, Exception> resolve(Map<String, Double> mapVars) {
    Try<Double, Exception> resolveLeft = left.resolve(mapVars);
    if (resolveLeft.isFailure()) {
      return resolveLeft;
    }

    Try<Double, Exception> resolveRight = right.resolve(mapVars);
    if (resolveRight.isFailure()) {
      return resolveRight;
    }

    Double apply = op.apply(resolveLeft.getSuccess(), resolveRight.getSuccess());
    return Double.isNaN(apply) ? new Try<>(new UnsupportedOperationException()) : new Try<>(apply);
  }

  @Override
  public String toString() {
    return left.toString() + " " + opCode + " " + right.toString();
  }

  @Override
  public List<String> parameteres() {
    List<String> list = new LinkedList<>();
    list.addAll(left.parameteres());
    list.addAll(right.parameteres());
    return list;
  }
}
