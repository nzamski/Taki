package Utils;

import Cards.CardColor;

import java.util.Scanner;

public class IOOperations {
  public static final Scanner USER_IO = new Scanner(System.in);
  public static final int NEW_GAME_CODE = 1;
  public static final int LOAD_GAME_CODE = 2;

  public static void print(String output) {
    System.out.println(output);
  }

  public static String getString() {
    return USER_IO.nextLine();
  }

  public static String getString(String message) {
    print(message);

    return USER_IO.nextLine();
  }

  public static int getNumber() {
    return Integer.parseInt(getString());
  }

  public static int getNumber(String message) {
    print(message);

    return getNumber();
  }

  public static char getCharacter() {
    return getString().charAt(0);
  }

  public static char getCharacter(String message) {
    print(message);

    return getCharacter();
  }

  public static boolean getUserApproval(String message) {
    final String USER_APPROVAL = "y";
    print(message + " (" + USER_APPROVAL + "/n)?");

    return getString().equalsIgnoreCase(USER_APPROVAL);
  }

  public static void printWelcome() {
    IOOperations.print(
        """
 .----------------.  .----------------.  .----------------.  .----------------.\s
| .--------------. || .--------------. || .--------------. || .--------------. |
| |  _________   | || |      __      | || |  ___  ____   | || |     _____    | |
| | |  _   _  |  | || |     /  \\     | || | |_  ||_  _|  | || |    |_   _|   | |
| | |_/ | | \\_|  | || |    / /\\ \\    | || |   | |_/ /    | || |      | |     | |
| |     | |      | || |   / ____ \\   | || |   |  __'.    | || |      | |     | |
| |    _| |_     | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |     _| |_    | |
| |   |_____|    | || ||____|  |____|| || | |____||____| | || |    |_____|   | |
| |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------'  '----------------'\s
""");
  }

  public static int getMenuItemCode() {
    return IOOperations.getNumber(
        String.format(
            """
(%d) START NEW GAME
(%d) LOAD AN EXISTING GAME""", NEW_GAME_CODE, LOAD_GAME_CODE));
  }

  public static CardColor getColor() {
    CardColor cardColor = CardColor.NO_COLOR;
    IOOperations.print("Please choose a color:");
    IOOperations.print(CardColor.getLegend());

    while (cardColor == CardColor.NO_COLOR) {
      switch (IOOperations.getCharacter()) {
        case 'G' -> cardColor = CardColor.GREEN;
        case 'R' -> cardColor = CardColor.RED;
        case 'Y' -> cardColor = CardColor.YELLOW;
        case 'B' -> cardColor = CardColor.BLUE;
        default -> IOOperations.print("Error: color doesn't exist. Please try again.");
      }
    }

    return cardColor;
  }
}
