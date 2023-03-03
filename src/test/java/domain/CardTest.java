package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("Card를 생성한다.")
    void createCardSuccess() {
        Shape diamond = Shape.DIAMOND;
        Letter two = Letter.TWO;

        Card card = new Card(diamond, two);

        assertThat(card.getShape()).isEqualTo(Shape.DIAMOND);
        assertThat(card.getValue().getExpression()).isEqualTo("2");
        assertThat(card.getValue().getScore()).isEqualTo(2);
    }

}
