package edu.austral.ingsis.math;

public enum BinaryOperators {
  SUM("+"),
  SUBSTRACTION("-"),
  MULTIPLICATION("*"),
  DIVSION("/"),

  POWER("^"),
  ;

  final String s;

  BinaryOperators(String s) {
    this.s = s;
  }

  @Override
  public String toString() {
    return s;
  }
}
