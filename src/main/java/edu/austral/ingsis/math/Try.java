package edu.austral.ingsis.math;

public class Try<T, F extends Exception> {

  private T success;
  private F failure;

  public Try(T success) {
    this.success = success;
  }

  public Try(F failure) {
    this.failure = failure;
  }

  public boolean isSuccess() {
    return success != null;
  }

  public boolean isFailure() {
    return failure != null;
  }

  public T getSuccess() {
    return this.success;
  }

  public F getFailure() {
    return this.failure;
  }
}
