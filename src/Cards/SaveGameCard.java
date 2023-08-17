package Cards;

import Entities.CardsVisitor;

public class SaveGameCard extends Card {

  public SaveGameCard() {
    super(CardColor.NO_COLOR);
  }

  @Override
  public void play(CardsVisitor cardsVisitor) {
    cardsVisitor.visitSaveGameCard(this);
  }
}
