import Entities.Game;
import Exceptions.CannotDrawCardException;
import Utils.IOOperations;
import Utils.GameStorage;

import java.io.IOException;

public class Main {
  public static Game game;

  public static void getGame() {
    game = new Game();
    IOOperations.print("Welcome to TAKI. Please choose an option:");
    int menuItemCode = IOOperations.getMenuItemCode();
    boolean isCodeValid = false;

    while (!isCodeValid) {
      switch (menuItemCode) {
        case IOOperations.NEW_GAME_CODE -> {
          try {
            game.initGame();
            isCodeValid = true;
          } catch (CloneNotSupportedException | CannotDrawCardException exception) {
            IOOperations.printError("Cannot initialize game -> " + exception.getMessage());
          }
        }

        case IOOperations.LOAD_GAME_CODE -> {
          String filePath = IOOperations.getString("Enter the file path to load from:");

          try {
            game = GameStorage.loadGame(filePath);
            isCodeValid = true;
            IOOperations.print("Game loaded.");
          } catch (IOException | ClassNotFoundException exception) {
            IOOperations.printError("Cannot load game -> " + exception.getMessage());
            menuItemCode = IOOperations.getMenuItemCode();
          }

          if (game.isFinished()) {
            IOOperations.printError("Cannot load the game, it's already over");
            menuItemCode = IOOperations.getMenuItemCode();
          }
        }
        default -> {
          IOOperations.printError("Invalid code, try again.");
          menuItemCode = IOOperations.getMenuItemCode();
        }
      }
    }
  }

  public static void saveValidGame() {
    boolean isPathValid = false;
    String filePath = IOOperations.getString("Enter the file path to save to:");

    while (!isPathValid) {
      try {
        GameStorage.saveGame(game, filePath);
        isPathValid = true;
      } catch (IOException exception) {
        IOOperations.printError("Cannot save game -> " + exception.getMessage());
        filePath = IOOperations.getString("Enter the file path to save to:");
      }
    }
  }

  public static void main(String[] args) {
    getGame();
    game.changeDidPlayerExit(false);
    IOOperations.printWelcome();

    try {
      game.runGame();
    } catch (CannotDrawCardException exception) {
      IOOperations.printError("Cannot proceed game run -> " + exception.getMessage());
    }

    if (game.didPlayerExit() && IOOperations.getUserApproval("Would you like to save the game?")) {
      saveValidGame();
      IOOperations.print("Game saved.");
    }
  }
}
