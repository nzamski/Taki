package Cards;

public enum CardNumber {
  ONE("One"),
  THREE("Three"),
  FOUR("Four"),
  FIVE("Five"),
  SIX("Six"),
  SEVEN("Seven"),
  EIGHT("Eight"),
  NINE("Nine");
  public final String NUMBER;

  CardNumber(String number) {
    this.NUMBER = number;
  }
}
