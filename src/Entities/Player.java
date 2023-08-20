package Entities;

import Cards.Card;
import Cards.CardColor;
import Cards.SaveGameCard;
import Exceptions.IllegalMoveException;
import Exceptions.InvalidPlayerAge;
import Utils.IOOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
  private String name;
  private int age;
  public List<Card> hand;

  public Player() {}

  public void initPlayer() {
    this.hand = new ArrayList<>();
    String name = IOOperations.getString("What's your name?");
    this.changeName(name);
    boolean isAgeValid = false;

    while (!isAgeValid) {
      int age = IOOperations.getNumber("What's your age?");

      try {
        this.changeAge(age);
        isAgeValid = true;
      } catch (InvalidPlayerAge exception) {
        IOOperations.printError(exception.getMessage());
      }
    }
  }

  public Card pickValidCard(Card topCard, boolean canEndTurn, boolean canExitGame, CardColor colorConstraint) {
    Card pickedCard = null;
    boolean isCardValid = false;
    boolean isHandShown = true;

    while (!isCardValid) {
      try {
        pickedCard = getCardFromPlayer(topCard, isHandShown, canEndTurn, canExitGame, colorConstraint);
        isCardValid = true;
      } catch (IllegalMoveException | IndexOutOfBoundsException exception) {
        IOOperations.printError(exception.getMessage());
        isHandShown = false;
      }
    }

    return pickedCard;
  }

  private Card getCardFromPlayer(
      Card topCard,
      boolean isHandShown,
      boolean canEndTurn,
      boolean canExitGame,
      CardColor colorConstraint)
      throws IllegalMoveException {
    final int SAVE_GAME_CODE = -1;
    final int DRAW_CARD_CODE = 0;
    final int END_TURN_CODE = 0;

    if (isHandShown) {
      IOOperations.print(String.format("Hey %s, this is your hand:", this.name()));

      if (canExitGame) {
        IOOperations.print("(" + SAVE_GAME_CODE + ") EXIT AND SAVE GAME");
      }

      if (canEndTurn) {
        IOOperations.print("(" + END_TURN_CODE + ") END TURN");
      } else {
        IOOperations.print("(" + DRAW_CARD_CODE + ") DRAW CARD");
      }

      for (int cardIndex = 0; cardIndex < this.hand.size(); cardIndex++) {
        IOOperations.print(
            String.format("(%d) %s", cardIndex + 1, this.hand.get(cardIndex).toString()));
      }
    }

    int chosenCode = IOOperations.getNumber("What would you like to play?");

    if (canExitGame && chosenCode == SAVE_GAME_CODE) {
      return new SaveGameCard();
    } else if (canEndTurn && chosenCode == END_TURN_CODE
        || !canEndTurn && chosenCode == DRAW_CARD_CODE) {
      return null;
    } else if (chosenCode < 0 || chosenCode > this.hand.size()) {
      throw new IllegalMoveException("Card index isn't valid");
    }

    Card pickedCard = this.removeCard(chosenCode - 1);

    if ((colorConstraint != null && !pickedCard.isCardValidInTakiRun(colorConstraint))
        || !topCard.isNextCardValid(pickedCard)) {
      this.addCard(chosenCode - 1, pickedCard);
      throw new IllegalMoveException("Cannot be placed upon current top card");
    }

    return pickedCard;
  }

  public String name() {
    return this.name;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public int age() {
    return this.age;
  }

  public void changeAge(int age) throws InvalidPlayerAge {
    final int MINIMAL_AGE = 5;

    if (age < MINIMAL_AGE) {
      throw new InvalidPlayerAge("Player's age cannot be less than " + MINIMAL_AGE);
    }

    this.age = age;
  }

  public void addCard(Card card) {
    this.hand.add(card);
  }

  public void addCard(int cardIndex, Card card) {
    this.hand.add(cardIndex, card);
  }

  public Card removeCard(int cardIndex) {
    return this.hand.remove(cardIndex);
  }

  public boolean isOutOfCards() {
    return this.hand.isEmpty();
  }
}
