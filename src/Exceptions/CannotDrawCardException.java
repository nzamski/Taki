package Exceptions;

public class CannotDrawCardException extends Exception {
  public CannotDrawCardException(String message) {
    super("CannotDrawCardException -> " + message);
  }
}
