package Entities;

import Cards.*;
import Exceptions.CannotDrawCardException;
import Utils.IOOperations;

public class PlayCardsVisitor implements CardsVisitor {
  private final Game GAME;

  public PlayCardsVisitor(Game game) {
    this.GAME = game;
  }

  @Override
  public void visitNumericCard(NumericCard card) {
    GAME.placeCard(card);
  }

  @Override
  public void visitStopCard(StopCard card) {
    GAME.advanceActivePlayer(1);
    GAME.placeCard(card);
  }

  @Override
  public void visitPlusTwoCard(PlusTwoCard card) {
    GAME.increasePlusTwoMultiplier();
    GAME.placeCard(card);
  }

  @Override
  public void visitChangeDirectionCard(ChangeDirectionCard card) {
    GAME.toggleDirection();
    GAME.placeCard(card);
  }

  @Override
  public void visitChangeColorCard(ChangeColorCard card) {
    card.changeColor(IOOperations.getColor());
    GAME.placeCard(card);
  }

  @Override
  public void visitTakiCard(TakiCard card) throws CannotDrawCardException {
    this.openAndCloseTaki(card, card.color());
  }

  @Override
  public void visitSuperTakiCard(SuperTakiCard card) throws CannotDrawCardException {
    if (GAME.leadingCard().color() == CardColor.NO_COLOR) {
      card.changeColor(IOOperations.getColor());
    } else {
      card.changeColor(GAME.leadingCard().color());
    }

    this.openAndCloseTaki(card, card.color());
  }

  private void openAndCloseTaki(Card pickedCard, CardColor takiColor)
      throws CannotDrawCardException {
    Card lastCard = GAME.leadingCard();
    IOOperations.print("Taki open");

    while (pickedCard != null) {
      GAME.placeCard(lastCard);
      lastCard = pickedCard;
      IOOperations.print("Top card is " + lastCard);
      pickedCard = GAME.activePlayer().pickValidCard(lastCard, true, false, takiColor);
    }

    if (lastCard instanceof TakiCard || lastCard instanceof SuperTakiCard) {
      GAME.advanceActivePlayer(1);
    } else {
      IOOperations.print("Taki closed");
    }

    GAME.playMove(lastCard, this);
  }

  @Override
  public void visitPlusCard(PlusCard card) throws CannotDrawCardException {
    GAME.placeCard(card);
    IOOperations.print("Top card is " + GAME.leadingCard());
    Card pickedCard = GAME.activePlayer().pickValidCard(GAME.leadingCard(), false, false, null);
    GAME.playMove(pickedCard, this);
  }

  @Override
  public void visitKingCard(KingCard card) throws CannotDrawCardException {
    GAME.deactivatePlusTwoMode();
    GAME.placeCard(card);
    IOOperations.print("Top card is " + GAME.leadingCard());
    Card pickedCard = GAME.activePlayer().pickValidCard(GAME.leadingCard(), true, false, null);

    if (pickedCard != null) {
      GAME.playMove(pickedCard, this);
    }
  }

  @Override
  public void visitSaveGameCard() {
    GAME.changeDidPlayerExit(true);
  }
}
