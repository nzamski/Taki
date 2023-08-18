package Cards;

import Entities.CardsVisitor;
import Exceptions.CannotDrawCardException;

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
  public void play(CardsVisitor cardsVisitor) throws CannotDrawCardException {
    cardsVisitor.visitPlusCard(this);
  }

  @Override
  public String toString() {
    return this.color().code + "Plus" + CardColor.RESET_CODE;
  }
}
