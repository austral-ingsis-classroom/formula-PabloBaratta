package edu.austral.ingsis.math;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public class UnaryExpression implements Function {

  private final Function fun;
  private final UnaryOperator<Double> op;
  private final UnaryOperators opCode;

  public UnaryExpression(Function fun, UnaryOperator<Double> operator, UnaryOperators opCode) {
    this.fun = fun;
    this.op = operator;
    this.opCode = opCode;
  }

  @Override
  public Try<Double, Exception> resolve() {
    return resolve(Map.of());
  }

  @Override
  public Try<Double, Exception> resolve(Map<String, Double> mapVars) {
    Try<Double, Exception> resolve = fun.resolve(mapVars);

    if (resolve().isFailure()) {
      return resolve;
    }

    Double apply = op.apply(resolve.getSuccess());

    return Double.isNaN(apply) ? new Try<>(new UnsupportedOperationException()) : new Try<>(apply);
  }

  @Override
  public String toString() {
    return opCode.left + fun.toString() + opCode.toString();
  }

  @Override
  public List<String> parameteres() {
    return fun.parameteres();
  }
}
