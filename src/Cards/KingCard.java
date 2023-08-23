package Cards;

import Entities.CardsVisitor;
import Exceptions.CannotDrawCardException;

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
  public void play(CardsVisitor cardsVisitor) throws CannotDrawCardException {
    cardsVisitor.visitKingCard(this);
  }

  @Override
  public String toString() {
    return this.color().CODE + "King" + CardColor.RESET_CODE;
  }
}
