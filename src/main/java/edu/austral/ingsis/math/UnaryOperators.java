package edu.austral.ingsis.math;

public enum UnaryOperators {
  ABS("|", "|"),
  PARENTHESIS("(", ")");

  public final String left;
  public final String right;

  UnaryOperators(String left, String right) {
    this.left = left;
    this.right = right;
  }
}
