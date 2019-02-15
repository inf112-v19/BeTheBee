package inf112.skeleton.app.gameLogic;

import inf112.skeleton.app.gameLogic.enums.CardType;

public class ProgramCard {
    private CardType cardType;
    private int priority;

    ProgramCard(CardType cardType, int priority){
        this.cardType = cardType;
        this.priority = priority;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getPriority() {
        return priority;
    }

    public String toString(){
        return cardType.getDescription() + " " + priority;
    }
}