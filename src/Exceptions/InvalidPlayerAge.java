package Exceptions;

public class InvalidPlayerAge extends Exception {
  public InvalidPlayerAge(String message) {
    super("InvalidPlayerAge -> " + message);
  }
}
