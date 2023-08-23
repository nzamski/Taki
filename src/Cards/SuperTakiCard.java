package Cards;

import Entities.CardsVisitor;
import Exceptions.CannotDrawCardException;

public class SuperTakiCard extends Card implements ColorChangeable {
  public static final int AMOUNT = 2;

  public SuperTakiCard() {
    super(CardColor.NO_COLOR);
  }

  @Override
  public void resetColor() {
    this.changeColor(CardColor.NO_COLOR);
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return nextCard instanceof SuperTakiCard
        || nextCard instanceof TakiCard
        || super.isNextCardValid(nextCard);
  }

  @Override
  public void play(CardsVisitor cardsVisitor) throws CannotDrawCardException {
    cardsVisitor.visitSuperTakiCard(this);
  }

  @Override
  public String toString() {
    return this.color().CODE + "Super Taki" + CardColor.RESET_CODE;
  }
}
