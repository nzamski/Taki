package Cards;

public class KingCard extends Card {
  public static final int AMOUNT = 2;

  public KingCard() {
    super(CardColor.NO_COLOR);
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return true;
  }

  @Override
  public String toString() {
    return this.color().code + "King" + CardColor.RESET.code;
  }
}
