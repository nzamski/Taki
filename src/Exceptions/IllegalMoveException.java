package Exceptions;

public class IllegalMoveException extends Exception {
  public IllegalMoveException(String message) {
    super("IllegalMoveException -> " + message);
  }
}
