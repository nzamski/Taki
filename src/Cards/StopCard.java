package Cards;

public class StopCard extends Card {
  public static final int AMOUNT = 2;

  public StopCard(CardColor color) {
    super(color);
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return nextCard instanceof StopCard || super.isNextCardValid(nextCard);
  }

  @Override
  public String toString() {
    return this.color().code + "Stop" + CardColor.RESET.code;
  }
}
