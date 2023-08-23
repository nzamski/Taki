package Cards;

import Entities.CardsVisitor;
import Exceptions.CannotDrawCardException;

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
  public void play(CardsVisitor cardsVisitor) throws CannotDrawCardException {
    cardsVisitor.visitTakiCard(this);
  }

  @Override
  public String toString() {
    return this.color().CODE + "Taki" + CardColor.RESET_CODE;
  }
}
