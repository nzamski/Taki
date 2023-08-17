package Cards;

import Entities.CardsVisitor;

public class ChangeColorCard extends Card implements ColorChangeable {
  public static final int AMOUNT = 4;

  public ChangeColorCard() {
    super(CardColor.NO_COLOR);
  }

  @Override
  public boolean resetColor() {
    CardColor oldColor = this.color();
    this.changeColor(CardColor.NO_COLOR);

    return this.color() != oldColor;
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
    return this.color().code + "Change Color" + CardColor.RESET.code;
  }
}
