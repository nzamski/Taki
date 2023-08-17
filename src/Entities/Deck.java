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
  private final Stack<Card> cards;

  public Deck(Stack<Card> cards) {
    this.cards = cards;
  }

  public Deck() {
    this.cards = new Stack<>();
  }

  public Stack<Card> cards() {
    return this.cards;
  }

  public Card drawCard() throws CannotDrawCardException {
    try {
      return this.cards.pop();
    } catch (EmptyStackException exception) {
      throw new CannotDrawCardException(exception.getMessage());
    }
  }

  public Card topCard() throws CannotDrawCardException {
    try {
      return this.cards.peek();
    } catch (EmptyStackException exception) {
      throw new CannotDrawCardException(exception.getMessage());
    }
  }

  public void insertCard(Card card) {
    this.cards.push(card);
  }

  public void loadMass(Card card, int amount) throws CloneNotSupportedException {
    for (int index = 0; index < amount; index++) {
      this.insertCard(card.clone());
    }
  }

  public void shuffleDeck() {
    Collections.shuffle(this.cards);

    for (Card card : this.cards) {
      if (card instanceof ColorChangeable) {
        ((ColorChangeable) card).resetColor();
      } else if (card instanceof PlusTwoCard) {
        ((PlusTwoCard) card).changeActivity(true);
      }
    }
  }

  public boolean isEmpty() {
    return this.cards.empty();
  }
}
