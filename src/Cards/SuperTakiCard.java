package Cards;

public class SuperTakiCard extends Card implements ColorChangeable {
  public static final int AMOUNT = 2;

  public SuperTakiCard() {
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
    return nextCard instanceof SuperTakiCard
        || nextCard instanceof TakiCard
        || super.isNextCardValid(nextCard);
  }

  @Override
  public String toString() {
    return this.color().code + "Super Taki" + CardColor.RESET.code;
  }
}
