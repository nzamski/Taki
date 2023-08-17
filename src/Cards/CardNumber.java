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
  public final String number;

  CardNumber(String number) {
    this.number = number;
  }
}
