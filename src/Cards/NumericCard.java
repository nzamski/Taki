package Cards;

import Entities.CardsVisitor;

public class NumericCard extends Card {
  private CardNumber number;
  public static final int AMOUNT = 2;

  public NumericCard(CardColor color, CardNumber number) {
    super(color);
    this.changeNumber(number);
  }

  public CardNumber number() {
    return this.number;
  }

  public void changeNumber(CardNumber number) {
    this.number = number;
  }

  @Override
  public boolean isNextCardValid(Card nextCard) {
    return (nextCard instanceof NumericCard && this.number == ((NumericCard) nextCard).number)
        || super.isNextCardValid(nextCard);
  }

  @Override
  public void play(CardsVisitor cardsVisitor) {
    cardsVisitor.visitNumericCard(this);
  }

  @Override
  public String toString() {
    return this.color().code + this.number().number + CardColor.RESET_CODE;
  }
}
