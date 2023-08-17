package Cards;

import java.io.Serializable;

public abstract class Card implements Cloneable, Serializable {
  protected CardColor color;

  public Card(CardColor color) {
    this.changeColor(color);
  }

  public CardColor color() {
    return this.color;
  }

  public void changeColor(CardColor color) {
    this.color = color;
  }

  public boolean isNextCardValid(Card nextCard) {
    return nextCard.color() == CardColor.NO_COLOR || nextCard.color() == this.color();
  }

  @Override
  public Card clone() throws CloneNotSupportedException {
    return (Card) super.clone();
  }

  @Override
  public String toString() {
    return this.color().code + "Card" + CardColor.RESET.code;
  }
}
