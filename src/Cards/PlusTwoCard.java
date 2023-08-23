package Cards;

import Entities.CardsVisitor;

public class PlusTwoCard extends Card {
  private boolean isActive;
  public static final int AMOUNT = 2;

  public PlusTwoCard(CardColor color) {
    super(color);
    this.isActive = false;
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    if (nextCard instanceof PlusTwoCard) {
      return true;
    } else if (this.isActive()) {
      return nextCard instanceof KingCard;
    }

    return super.isNextCardValid(nextCard);
  }

  public boolean isActive() {
    return this.isActive;
  }

  @Override
  public void play(CardsVisitor cardsVisitor) {
    cardsVisitor.visitPlusTwoCard(this);
  }

  public void changeActivity(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    return this.color().code + "Plus Two" + CardColor.RESET_CODE;
  }
}
