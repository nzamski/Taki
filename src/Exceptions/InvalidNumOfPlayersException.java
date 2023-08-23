package Exceptions;

public class InvalidNumOfPlayersException extends Exception {
  public InvalidNumOfPlayersException(String message) {
    super("InvalidNumOfPlayersException -> " + message);
  }
}
