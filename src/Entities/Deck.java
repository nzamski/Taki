package Entities;

import Cards.Card;
import Cards.ColorChangeable;
import Cards.PlusTwoCard;
import Exceptions.CannotDrawCardException;

import java.io.Serializable;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class Deck implements Serializable {
  private final Stack<Card> CARDS;

  public Deck() {
    this.CARDS = new Stack<>();
  }

  public Stack<Card> cards() {
    return this.CARDS;
  }

  public Card drawCard() throws CannotDrawCardException {
    try {
      return this.CARDS.pop();
    } catch (EmptyStackException exception) {
      throw new CannotDrawCardException(exception.getMessage());
    }
  }

  public Card topCard() throws CannotDrawCardException {
    try {
      return this.CARDS.peek();
    } catch (EmptyStackException exception) {
      throw new CannotDrawCardException(exception.getMessage());
    }
  }

  public void insertCard(Card card) {
    this.CARDS.push(card);
  }

  public void loadMass(Card card, int amount) throws CloneNotSupportedException {
    for (int index = 0; index < amount; index++) {
      this.insertCard(card.clone());
    }
  }

  public void shuffleDeck() {
    Collections.shuffle(this.CARDS);

    for (Card card : this.CARDS) {
      if (card instanceof ColorChangeable) {
        ((ColorChangeable) card).resetColor();
      } else if (card instanceof PlusTwoCard) {
        ((PlusTwoCard) card).changeActivity(true);
      }
    }
  }

  public boolean isEmpty() {
    return this.CARDS.empty();
  }
}
