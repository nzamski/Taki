package Cards;

public class ChangeDirectionCard extends Card {
  public static final int AMOUNT = 2;

  public ChangeDirectionCard(CardColor color) {
    super(color);
  }

  @Override
  public String toString() {
    return this.color().code + "Change Direction" + CardColor.RESET.code;
  }
}
