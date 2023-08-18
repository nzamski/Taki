package Entities;

import Cards.*;
import Exceptions.CannotDrawCardException;
import Exceptions.InvalidNumOfPlayers;
import Utils.IOOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Game implements Serializable {
  private Deck stack;
  private Deck pile;
  private DirectionIndex direction;
  private List<Player> players;
  private int numPlayers;
  private int activePlayerIndex;
  private int plusTwoMultiplier;
  private boolean isFinished;
  private boolean didPlayerExit;

  public Game() {}

  public void initGame() throws CloneNotSupportedException, CannotDrawCardException {
    this.pile = new Deck();
    this.stack = new Deck();
    this.direction = DirectionIndex.FORWARD;
    this.players = new ArrayList<>();
    this.activePlayerIndex = 0;
    this.plusTwoMultiplier = 0;
    this.isFinished = false;
    this.didPlayerExit = false;
    this.loadStack();
    this.drawOpeningCard();
    this.loadPlayers();
    this.divideCards();
  }

  private void loadStack() throws CloneNotSupportedException {
    for (CardColor cardColor : CardColor.values()) {
      if (cardColor != CardColor.NO_COLOR && cardColor != CardColor.RESET) {
        for (CardNumber cardNumber : CardNumber.values()) {
          this.stack.loadMass(new NumericCard(cardColor, cardNumber), NumericCard.AMOUNT);
        }

        this.stack.loadMass(new StopCard(cardColor), StopCard.AMOUNT);
        this.stack.loadMass(new ChangeDirectionCard(cardColor), ChangeDirectionCard.AMOUNT);
        this.stack.loadMass(new PlusCard(cardColor), PlusCard.AMOUNT);
        this.stack.loadMass(new PlusTwoCard(cardColor), PlusTwoCard.AMOUNT);
        this.stack.loadMass(new TakiCard(cardColor), TakiCard.AMOUNT);
      }
    }

    this.stack.loadMass(new ChangeColorCard(), ChangeColorCard.AMOUNT);
    this.stack.loadMass(new SuperTakiCard(), SuperTakiCard.AMOUNT);
    this.stack.loadMass(new KingCard(), KingCard.AMOUNT);
    System.out.println(this.stack.cards().size() + " cards loaded into the game");
    this.stack.shuffleDeck();
  }

  private void drawOpeningCard() throws CannotDrawCardException {
    Stack<Card> tempStack = new Stack<>();
    Card openingCard = this.stack.drawCard();

    if (openingCard.color() == CardColor.NO_COLOR) {
      tempStack.add(openingCard);
      openingCard = this.stack.drawCard();
    }

    this.pile.insertCard(openingCard);
    for (Card card : tempStack) {
      this.stack.insertCard(card);
    }
  }

  private void loadPlayers() {
    boolean isNumPlayersValid = false;

    while (!isNumPlayersValid) {
      try {
        this.changeNumPlayers(IOOperations.getNumber("How many players?"));
        isNumPlayersValid = true;
      } catch (InvalidNumOfPlayers exception) {
        IOOperations.print(exception.getMessage());
      }
    }

    for (int playerIndex = 0; playerIndex < this.numPlayers(); playerIndex++) {
      IOOperations.print(String.format("Player %d of %d", playerIndex + 1, this.numPlayers()));
      Player currPlayer = new Player();
      currPlayer.initPlayer();
      this.players.add(currPlayer);
    }

    this.players.sort(Comparator.comparingInt(Player::age));
  }

  private void divideCards() throws CannotDrawCardException {
    final int CARDS_PER_PLAYER = 8;

    for (int cardIndex = 0; cardIndex < CARDS_PER_PLAYER; cardIndex++) {
      for (Player player : this.players) {
        player.addCard(this.stack.drawCard());
      }
    }
  }

  public void runGame() throws CannotDrawCardException {
    CardsVisitor cardsVisitor = new PlayCardsVisitor(this);

    while (!this.isFinished() && !this.didPlayerExit()) {
      if (this.stack.isEmpty()) {
        IOOperations.print("Stack refilled");
        this.refillStack();
      }

      IOOperations.print("Top card is " + this.pile.topCard());
      Player currPlayer = this.activePlayer();
      Card pickedCard = currPlayer.pickValidCard(this.pile.topCard(), false);
      this.playMove(pickedCard, cardsVisitor);
      IOOperations.print("");

      if (currPlayer.isOutOfCards()) {
        IOOperations.print(currPlayer.name() + " is the winner!");
        this.finishGame();
      } else if (!this.didPlayerExit()) {
        this.advanceActivePlayer(1);
      }
    }
  }

  public void playMove(Card card, CardsVisitor cardsVisitor) throws CannotDrawCardException {
    if (card == null) {
      this.drawCards(this.activePlayer());
    } else {
      card.play(cardsVisitor);
    }
  }

  private void refillStack() throws CannotDrawCardException {
    Card currCard = this.pile.drawCard();

    while (!this.pile.isEmpty()) {
      this.stack.insertCard(currCard);
      currCard = this.pile.drawCard();
    }

    this.pile.insertCard(currCard);
    this.stack.shuffleDeck();
  }

  private void drawCards(Player currPlayer) throws CannotDrawCardException {
    if (this.plusTwoMultiplier() == 0) {
      currPlayer.addCard(this.stack.drawCard());
    } else {
      for (int cardIndex = 0; cardIndex < this.plusTwoMultiplier() * 2; cardIndex++) {
        currPlayer.addCard(this.stack.drawCard());
      }

      IOOperations.print("You've taken " + this.plusTwoMultiplier() * 2 + " cards.");
      this.deactivatePlusTwoMode();
    }
  }

  public Card leadingCard() throws CannotDrawCardException {
    return this.pile.topCard();
  }

  public void placeCard(Card card) {
    this.pile.insertCard(card);
  }

  public DirectionIndex direction() {
    return this.direction;
  }

  public void toggleDirection() {
    this.direction = this.direction.toggle();
  }

  public int numPlayers() {
    return this.numPlayers;
  }

  public void changeNumPlayers(int numPlayers) throws InvalidNumOfPlayers {
    final int MINIMAL_PARTICIPANTS = 2;
    final int MAXIMAL_PARTICIPANTS = 10;

    if (numPlayers < MINIMAL_PARTICIPANTS) {
      throw new InvalidNumOfPlayers(
          "Number of players cannot be less than " + MINIMAL_PARTICIPANTS);
    } else if (numPlayers > MAXIMAL_PARTICIPANTS) {
      throw new InvalidNumOfPlayers(
          "Number of players cannot be greater than " + MAXIMAL_PARTICIPANTS);
    }

    this.numPlayers = numPlayers;
  }

  public Player activePlayer() {
    return this.players.get(this.activePlayerIndex);
  }

  public void advanceActivePlayer(int step) {
    this.activePlayerIndex =
        (step * this.direction().index + this.activePlayerIndex + this.numPlayers())
            % this.numPlayers();
  }

  public int plusTwoMultiplier() {
    return this.plusTwoMultiplier;
  }

  public void increasePlusTwoMultiplier() {
    this.plusTwoMultiplier++;
  }

  public void deactivatePlusTwoMode() throws CannotDrawCardException {
    this.plusTwoMultiplier = 0;

    if (this.pile.topCard() instanceof PlusTwoCard) {
      ((PlusTwoCard) this.pile.topCard()).changeActivity(false);
    }
  }

  public boolean isFinished() {
    return this.isFinished;
  }

  public void finishGame() {
    this.isFinished = true;
  }

  public boolean didPlayerExit() {
    return this.didPlayerExit;
  }

  public void changeDidPlayerExit(boolean didPlayerExit) {
    this.didPlayerExit = didPlayerExit;
  }
}
