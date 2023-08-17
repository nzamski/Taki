package Cards;

public class TakiCard extends Card {
  public static final int AMOUNT = 2;

  public TakiCard(CardColor color) {
    super(color);
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return nextCard instanceof TakiCard
        || nextCard instanceof SuperTakiCard
        || super.isNextCardValid(nextCard);
  }

  @Override
  public String toString() {
    return this.color().code + "Taki" + CardColor.RESET.code;
  }
}
