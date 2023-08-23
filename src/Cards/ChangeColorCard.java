package Cards;

import Entities.CardsVisitor;

public class ChangeColorCard extends Card implements ColorChangeable {
  public static final int AMOUNT = 4;

  public ChangeColorCard() {
    super(CardColor.NO_COLOR);
  }

  @Override
  public void resetColor() {
    this.changeColor(CardColor.NO_COLOR);
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return nextCard instanceof ChangeColorCard || super.isNextCardValid(nextCard);
  }

  @Override
  public void play(CardsVisitor cardsVisitor) {
    cardsVisitor.visitChangeColorCard(this);
  }

  @Override
  public String toString() {
    return this.color().CODE + "Change Color" + CardColor.RESET_CODE;
  }
}
