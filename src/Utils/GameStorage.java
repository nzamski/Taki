package Utils;

import Entities.Game;

import java.io.*;

public class GameStorage {
  public static void saveGame(Game game, String filePath) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream(filePath);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

    objectOutputStream.writeObject(game);
  }

  public static Game loadGame(String filePath) throws IOException, ClassNotFoundException {
    FileInputStream fileInputStream = new FileInputStream(filePath);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

    return (Game) objectInputStream.readObject();
  }
}
