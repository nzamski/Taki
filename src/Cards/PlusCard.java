package Cards;

public class PlusCard extends Card {
  public static final int AMOUNT = 2;

  public PlusCard(CardColor color) {
    super(color);
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return nextCard instanceof PlusCard || super.isNextCardValid(nextCard);
  }

  @Override
  public String toString() {
    return this.color().code + "Plus" + CardColor.RESET.code;
  }
}
