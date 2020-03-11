package view;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

public class OutputView {

    public static void printFirstDealOutResult(Dealer dealer, Players players) {
        System.out.println(String.format("\n딜러와 %s에게 2장을 나누었습니다.", players.getAllNames()));
        System.out.println(dealer.getDrawResult());
        System.out.println(players.getAllFirstDrawResult());
    }

    public static void printDealOutResult(Player player) {
        System.out.println(player.getDrawResult());
    }

    public static void printDealerDealOut() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
