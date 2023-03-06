package domain;

public class Dealer extends Participant {

    private static final int MORE_CARD_LIMIT = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer(CardDeck cardDeck) {
        super(DEALER_NAME, cardDeck);
    }

    public Card showOneCard() {
        return cardDeck.getFirstCard();
    }

    public boolean isMoreCardAble() {
        return getTotalScore() <= MORE_CARD_LIMIT;
    }

    public int getTotalScore() {
        return cardDeck.calculateScore(MORE_CARD_LIMIT);
    }

}
