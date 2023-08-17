package Cards;

import Entities.CardsVisitor;

public class PlusTwoCard extends Card {
  public static final int AMOUNT = 2;
  private boolean isActive;

  public PlusTwoCard(CardColor color) {
    super(color);
    this.isActive = false;
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    if (!this.isActive()) {
      return super.isNextCardValid(nextCard);
    }

    return nextCard instanceof PlusTwoCard || nextCard instanceof KingCard;
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
    return this.color().code + "Plus Two" + CardColor.RESET.code;
  }
}
