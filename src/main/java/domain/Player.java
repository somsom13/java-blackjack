package domain;

public class Player extends Participant {

    private static final String BAN_NAME_ERROR_MESSAGE = "Player 의 이름은 딜러일 수 없습니다.";
    private static final Score MORE_CARD_LIMIT = Score.from(21);

    private Player(Name name, Hand hand) {
        super(name, hand);
        validate(name);
    }

    public static Player of(Name name, Hand hand) {
        return new Player(name, hand);
    }

    private void validate(Name name) {
        if (name.equals(Name.DEALER)) {
            throw new IllegalArgumentException(BAN_NAME_ERROR_MESSAGE);
        }
    }

    public boolean isMoreCardAble() {
        return MORE_CARD_LIMIT.isGreaterThan(getTotalScore());
    }

    public Score getTotalScore() {
        return hand.calculateScore(MORE_CARD_LIMIT);
    }

}
