package Cards;

public enum CardColor {
  NO_COLOR('N', ""),
  GREEN('G', "\u001B[32m"),
  RED('R', "\u001B[31m"),
  YELLOW('Y', "\u001B[33m"),
  BLUE('B', "\u001B[34m"),
  RESET('R', "\033[0m");

  public final char character;
  public final String code;

  CardColor(char character, String code) {
    this.character = character;
    this.code = code;
  }

  public static String getLegend() {
    return String.format(
        """
            (%c) Green
            (%c) Red
            (%c) Yellow
            (%c) Blue""",
        GREEN.character, RED.character, YELLOW.character, BLUE.character);
  }
}
