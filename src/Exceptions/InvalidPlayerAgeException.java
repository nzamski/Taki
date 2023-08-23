package Exceptions;

public class InvalidPlayerAgeException extends Exception {
  public InvalidPlayerAgeException(String message) {
    super("InvalidPlayerAgeException -> " + message);
  }
}
