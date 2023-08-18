package Entities;

import Cards.*;
import Exceptions.CannotDrawCardException;

public interface CardsVisitor {
    void visitNumericCard(NumericCard card);
    void visitStopCard(StopCard card);
    void visitPlusTwoCard(PlusTwoCard card);
    void visitChangeDirectionCard(ChangeDirectionCard card);
    void visitChangeColorCard(ChangeColorCard card);
    void visitTakiCard(TakiCard card) throws CannotDrawCardException;
    void visitSuperTakiCard(SuperTakiCard card) throws CannotDrawCardException;
    void visitPlusCard(PlusCard card) throws CannotDrawCardException;
    void visitKingCard(KingCard card) throws CannotDrawCardException;
    void visitSaveGameCard();

}
