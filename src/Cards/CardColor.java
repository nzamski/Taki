package Cards;

public enum CardColor {
  NO_COLOR('N', ""),
  GREEN('G', "\u001B[32m"),
  RED('R', "\u001B[31m"),
  YELLOW('Y', "\u001B[33m"),
  BLUE('B', "\u001B[34m");

  public final char CHARACTER;
  public final String CODE;
  public final static String RESET_CODE = "\033[0m";

  CardColor(char character, String code) {
    this.CHARACTER = character;
    this.CODE = code;
  }

  public static String getLegend() {
    return String.format(
        """
            (%c) Green
            (%c) Red
            (%c) Yellow
            (%c) Blue""",
        GREEN.CHARACTER, RED.CHARACTER, YELLOW.CHARACTER, BLUE.CHARACTER);
  }
}
