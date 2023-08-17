package Exceptions;

public class InvalidNumOfPlayers extends Exception {
  public InvalidNumOfPlayers(String message) {
    super("InvalidNumOfPlayers -> " + message);
  }
}
