package Entities;

import Cards.*;
import Exceptions.CannotDrawCardException;
import Utils.IOOperations;

public class PlayCardsVisitor implements CardsVisitor {
  private final Game game;

  public PlayCardsVisitor(Game game) {
    this.game = game;
  }

  @Override
  public void visitNumericCard(NumericCard card) {
    game.placeCard(card);
  }

  @Override
  public void visitStopCard(StopCard card) {
    game.advanceActivePlayer(1);
    game.placeCard(card);
  }

  @Override
  public void visitPlusTwoCard(PlusTwoCard card) {
    game.increasePlusTwoMultiplier();
    game.placeCard(card);
  }

  @Override
  public void visitChangeDirectionCard(ChangeDirectionCard card) {
    game.toggleDirection();
    game.placeCard(card);
  }

  @Override
  public void visitChangeColorCard(ChangeColorCard card) {
    card.changeColor(IOOperations.getColor());
    game.placeCard(card);
  }

  @Override
  public void visitTakiCard(TakiCard card) throws CannotDrawCardException {
    this.openAndCloseTaki(card);
  }

  @Override
  public void visitSuperTakiCard(SuperTakiCard card) throws CannotDrawCardException {
    if (game.leadingCard().color() == CardColor.NO_COLOR) {
      card.changeColor(IOOperations.getColor());
    } else {
      card.changeColor(game.leadingCard().color());
    }

    this.openAndCloseTaki(card);
  }

  private void openAndCloseTaki(Card pickedCard) throws CannotDrawCardException {
    Card lastCard = game.leadingCard();
    IOOperations.print("Taki open");

    while (pickedCard != null) {
      game.placeCard(lastCard);
      lastCard = pickedCard;
      IOOperations.print("Top card is " + lastCard);
      pickedCard = game.activePlayer().pickValidCard(lastCard, true);
    }

    if (lastCard instanceof TakiCard || lastCard instanceof SuperTakiCard) {
      game.advanceActivePlayer(1);
    } else {
      IOOperations.print("Taki closed");
    }

    game.playMove(lastCard, this);
  }

  @Override
  public void visitPlusCard(PlusCard card) throws CannotDrawCardException {
    game.placeCard(card);
    IOOperations.print("Top card is " + game.leadingCard());
    Card pickedCard = game.activePlayer().pickValidCard(game.leadingCard(), false);
    game.playMove(pickedCard, this);
  }

  @Override
  public void visitKingCard(KingCard card) throws CannotDrawCardException {
    game.deactivatePlusTwoMode();
    game.placeCard(card);
    IOOperations.print("Top card is " + game.leadingCard());
    Card pickedCard = game.activePlayer().pickValidCard(game.leadingCard(), false);
    game.playMove(pickedCard, this);
  }

  @Override
  public void visitSaveGameCard() {
    game.changeDidPlayerExit(true);
  }
}
