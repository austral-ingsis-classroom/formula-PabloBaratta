package edu.austral.ingsis.math;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class BinaryMapper {

  Map<BinaryOperators, BinaryOperator<Double>> operaciones = new EnumMap<>(BinaryOperators.class);

  public BinaryMapper() {
    operaciones.put(BinaryOperators.SUM, Double::sum);
    operaciones.put(BinaryOperators.SUBSTRACTION, (x, y) -> x - y);
    operaciones.put(BinaryOperators.MULTIPLICATION, (x, y) -> x * y);
    operaciones.put(BinaryOperators.DIVSION, (x, y) -> y != 0 ? x / y : Double.NaN);
    operaciones.put(
        BinaryOperators.POWER, (x, y) -> x < 0 && y % 2 == 0 ? Double.NaN : Math.pow(x, y));
  }

  public void add(BinaryOperators op, BinaryOperator<Double> operator) {
    operaciones.put(op, operator);
  }
}
