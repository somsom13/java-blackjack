package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @Test
    @DisplayName("Hand 를 생성한다.")
    void createCardsSuccess() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.TWO), new Card(Shape.HEART, Letter.ACE));

        Hand hand = new Hand(initialCards);

        assertThat(hand.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("Player 의 카드 점수를 계산한다.")
    void calculateCardScorePlayer() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.TWO), new Card(Shape.HEART, Letter.ACE));
        Score limit = new Score(21);
        Score expectedScore = new Score(13);

        Hand hand = new Hand(initialCards);

        assertThat(hand.calculateScore(limit)).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("Player 의 Ace 를 2개 낮춰야 하는 경우 카드 점수를 계산한다.")
    void calculateCardScoreWhenAceDecreaseTwice() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.ACE), new Card(Shape.DIAMOND, Letter.JACK),new Card(Shape.HEART, Letter.ACE));
        Score limit = new Score(21);
        Score expectedScore = new Score(12);

        Hand hand = new Hand(initialCards);

        assertThat(hand.calculateScore(limit)).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("Dealer 의 카드 점수를 계산한다.")
    void calculateCardScoreOfDealer() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.ACE), new Card(Shape.DIAMOND, Letter.NINE));
        Score limit = new Score(16);
        Score expectedScore = new Score(10);

        Hand hand = new Hand(initialCards);

        assertThat(hand.calculateScore(limit)).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("블랙잭일 때 Dealer 의 카드 점수를 계산한다.")
    void calculateCardScoreOfDealerWhenBlackJack() {
        List<Card> initialCards = List.of(new Card(Shape.DIAMOND, Letter.ACE), new Card(Shape.DIAMOND, Letter.JACK));
        Score limit = new Score(16);
        Score expectedScore = new Score(21);

        Hand hand = new Hand(initialCards);

        assertThat(hand.calculateScore(limit)).isEqualTo(expectedScore);
    }

}