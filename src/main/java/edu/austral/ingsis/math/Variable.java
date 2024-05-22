package edu.austral.ingsis.math;

import java.util.List;
import java.util.Map;

public class Variable implements Function {

  private final String name;

  public Variable(String name) {
    this.name = name;
  }

  @Override
  public Try<Double, Exception> resolve() {
    return new Try<>(
        new UnsupportedOperationException(
            "You cannot operate resolve without arguments on an expression with a variable"));
  }

  @Override
  public Try<Double, Exception> resolve(Map<String, Double> mapVars) {
    Double value = mapVars.get(name);
    if (value == null) {
      return new Try<>(
          new UnsupportedOperationException("You must specify vairable with name" + this.name));
    }
    return new Try<>(value);
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public List<String> parameteres() {
    return List.of(name);
  }
}
