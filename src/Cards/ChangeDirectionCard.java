package Cards;

import Entities.CardsVisitor;

public class ChangeDirectionCard extends Card {
  public static final int AMOUNT = 2;

  public ChangeDirectionCard(CardColor color) {
    super(color);
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return nextCard instanceof ChangeDirectionCard || super.isNextCardValid(nextCard);
  }

  @Override
  public void play(CardsVisitor cardsVisitor) {
    cardsVisitor.visitChangeDirectionCard(this);
  }

  @Override
  public String toString() {
    return this.color().code + "Change Direction" + CardColor.RESET.code;
  }
}
