package edu.austral.ingsis.math;

import java.util.List;
import java.util.Map;

public class Value implements Function {

  private final double value;

  public Value(double value) {
    this.value = value;
  }

  @Override
  public Try<Double, Exception> resolve() {
    return new Try<>(value);
  }

  @Override
  public Try<Double, Exception> resolve(Map<String, Double> mapVars) {
    return resolve();
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public List<String> parameteres() {
    return List.of();
  }
}
