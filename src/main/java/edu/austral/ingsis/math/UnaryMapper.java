package edu.austral.ingsis.math;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class UnaryMapper {
  Map<UnaryOperators, UnaryOperator<Double>> operaciones = new EnumMap<>(UnaryOperators.class);

  public UnaryMapper() {
    operaciones.put(UnaryOperators.ABS, Math::abs);
    operaciones.put(UnaryOperators.PARENTHESIS, x -> x);
  }

  public void add(UnaryOperators op, UnaryOperator<Double> operator) {
    operaciones.put(op, operator);
  }
}
