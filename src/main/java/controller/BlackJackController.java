package controller;

import domain.Card;
import domain.CardDistributor;
import domain.Dealer;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.Result;
import dto.CardStatusDto;
import java.util.List;
import java.util.stream.Collectors;
import util.InitialCardMaker;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public BlackJackController() {
    }

    public void run() {
        CardDistributor cardDistributor = new CardDistributor(InitialCardMaker.generate());
        Players players = createGamePlayers(cardDistributor);
        Dealer dealer = new Dealer(cardDistributor.distributeInitialCard());
        printInitialDistribution(players, dealer);

        progress(players, cardDistributor, dealer);
        end(players, dealer);
    }

    private Players createGamePlayers(CardDistributor cardDistributor) {
        List<String> playerNames = inputView.requestPlayerName();
        List<Player> players = playerNames.stream()
                .map(name -> distributeInitialCardForPlayer(name, cardDistributor))
                .collect(Collectors.toList());
        return Players.from(players);
    }

    private Player distributeInitialCardForPlayer(String playerName, CardDistributor cardDistributor) {
        return Player.of(new Name(playerName), cardDistributor.distributeInitialCard());
    }

    private void printInitialDistribution(Players players, Dealer dealer) {
        outputView.printFirstCardDistribution(dealer.getNameToValue(), players.getPlayerNamesToString());
        printDealerInitialCard(dealer);
        printPlayersInitialCard(players);
    }

    private void printDealerInitialCard(Dealer dealer) {
        outputView.printCardStatus(dealer.getNameToValue(),
                getCardStatusFromCards(List.of(dealer.showOneCard())));
    }

    private List<CardStatusDto> getCardStatusFromCards(List<Card> cards) {
        return cards.stream()
                .map(CardStatusDto::from)
                .collect(Collectors.toList());
    }

    private void printPlayersInitialCard(Players players) {
        for (Participant player : players.getPlayers()) {
            outputView.printCardStatus(player.getNameToValue(),
                    getCardStatusFromCards(player.getCardList()));
        }
    }

    private void progress(Players players, CardDistributor cardDistributor, Dealer dealer) {
        progressPlayers(players, cardDistributor);
        progressDealer(dealer, cardDistributor);
    }

    private void progressPlayers(Players players, CardDistributor cardDistributor) {
        for (Player player : players.getPlayers()) {
            requestPlayerMoreCard(cardDistributor, player);
        }
    }

    private void requestPlayerMoreCard(CardDistributor cardDistributor, Player player) {
        boolean isCardRequested = true;

        while (player.isMoreCardAble() && isCardRequested) {
            isCardRequested = inputView.askMoreCard(player.getNameToValue());
            pickPlayerCardIfRequested(cardDistributor, player, isCardRequested);
        }
    }

    private void pickPlayerCardIfRequested(CardDistributor cardDistributor, Player player, boolean isCardRequested) {
        if (isCardRequested && cardDistributor.isCardLeft()) {
            player.pick(cardDistributor.distribute());
        }
        outputView.printCardStatus(player.getNameToValue(),
                getCardStatusFromCards(player.getCardList()));
    }

    private void progressDealer(Dealer dealer, CardDistributor cardDistributor) {
        int dealerMoreCardCount = 0;
        while (dealer.isMoreCardAble() && cardDistributor.isCardLeft()) {
            dealer.pick(cardDistributor.distribute());
            dealerMoreCardCount++;
        }
        outputView.printDealerMoreCard(dealer.getNameToValue(), dealerMoreCardCount);
    }

    private void end(Players players, Dealer dealer) {
        printFinalCard(dealer);
        players.getPlayers().forEach(this::printFinalCard);
        outputView.printFinalResult(dealer.getNameToValue(), new Result(dealer, players).getResult());
    }

    private void printFinalCard(Participant participant) {
        outputView.printCardAndScore(participant.getNameToValue(),
                getCardStatusFromCards(participant.getCardList()),
                participant.getTotalScoreToValue());
    }

}
