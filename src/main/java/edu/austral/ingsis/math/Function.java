package edu.austral.ingsis.math;

import java.util.List;
import java.util.Map;

public interface Function {
  Try<Double, Exception> resolve();

  Try<Double, Exception> resolve(Map<String, Double> mapVars);

  String toString();

  List<String> parameteres();
}
