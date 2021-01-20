/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war3.controllers;

import war3.ui.UserInterface;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import war3.domain.Card;
import war3.domain.CardType;
import war3.domain.Deck;
import war3.domain.Player;
import war3.domain.Suit;
import war3.domain.WildCard;
import war3.domain.WildSuit;

/**
 *
 * @author Michael
 */
public class Game {

    private String deckFile;
    private Deck deck;
    private List<Player> players;
    private Table table;
    private UserInterface ui;

    public Game(String deckFile) {
        this.deckFile = deckFile;
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.table = new Table();
        this.ui = new UserInterface();
    }

    ////Build & Sort Methods////
    public void populateDeck() {

        try (Scanner reader = new Scanner(Paths.get(this.deckFile))) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(", ");

                if (parts[0].equals("JOKER")) {
                    WildSuit suit = WildSuit.valueOf(parts[1]);
                    deck.add(new WildCard(suit));   //Delete this line to play without jokers//

                } else {
                    CardType type = CardType.valueOf(parts[0]);
                    Suit suit = Suit.valueOf(parts[1]);
                    deck.add(new Card(type, suit));
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void buildPlayers(List<String> playerNames) {
        playerNames.stream()
                .map(string -> new Player(string))
                .forEach(player -> this.players.add(player));

        ui.printPlayerList(players);
    }

    public void randomisePlayerOrder() {
        Collections.shuffle(players);
    }

    public void shuffle() {
        deck.shuffle();
    }

    public void deal() {

        int i = 0;

        while (deck.size() > 0) {
            Card card = deck.draw();
            players.get(i).addToHand(card);
            i++;

            if (i > players.size() - 1) {
                i = 0;
            }
        }
    }

    ////Game Phases////
    public void setup() {
        ui.printWelcomeMessage();

        while (true) {
            int choice = ui.menu();

            if (choice == 1) {
                break;
            }
            if (choice == 2) {
                System.out.println(deck);
            }
            if (choice == 3) {
                ui.printFarewellMessage();
                System.exit(0);
            }
        }

        int n = ui.queryNumberOfPlayers();
        List<String> playerList = ui.queryPlayerNames(n);

        buildPlayers(playerList);
        
//        randomisePlayerOrder();        //Uncomment to randomise player order//
    }

    public boolean playersAreReady() {
        return ui.queryReadyStatus();
    }

    public void shuffleAndDeal() {

        ui.printShufflingMessage();
        deck.shuffle();
        wait(800);

        ui.printDealingMessage();
        deal();
        wait(800);

        ui.printPlayerList(players);
    }

    public void playRound() {
        flipCardsOnTable();

        Player highest = table.findHighest();
        List<Player> highList = table.jointWinners(highest);

        if (!table.checkIfTie(highList)) {
            assignWinner(highest);

        } else {
            assignTie(highList);
            war(highList);
        }
    }

    public void podium() {
        List<Player> winners = getGameWinner();

        if (winners.size() == 1) {
            ui.printVictoryMessage(winners.get(0));

        } else {
            ui.printNoVictoryMessage();
        }
        ui.promptForReturnToMenu();

        setup();
    }

    ////Game Mechanics////
    void flipCardsOnTable() {

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            if (ui.promptForCardFlip(player)) {
                Card card = player.flip();
                table.add(player, card);

                System.out.println(player.getName() + ": " + card);
                wait(800);
            }
        }
    }

    void collectWinnings(List<Card> cards, Player player) {
        cards.forEach(card -> player.addToHand(card));
    }

    void war(List<Player> warringPlayers) {
        table.moveToBonusCards();

        ui.warCountdown();

        for (int i = 0; i < warringPlayers.size(); i++) {
            Player player = players.get(i);

            int j = 0;
            while (player.numberOfCards() > 1 && j < 4) {
                Card bonusCard = player.flip();
                table.addBonusCard(bonusCard);
                j++;
            }
        }
        playRound();
    }

    ////Helper Methods////
    void assignWinner(Player highest) {
        ui.printWinner(highest.getName());

        List<Card> winnings = table.pickUpCards();

        collectWinnings(winnings, highest);

        ui.waitForPlayerPress();
        ui.printWinnings(winnings);

        ui.waitForPlayerPress();
        ui.printPlayerList(players);
    }

    void assignTie(List<Player> highList) {
        ui.printTieMessage(highList);
        ui.waitForPlayerPress();
    }

    public boolean playersHaveCards() {
        long n = players.stream()
                .filter(player -> player.numberOfCards() > 0)
                .count();

        return (n > 1);
    }

    List<Player> getGameWinner() {
        List<Player> winners = players.stream()
                .filter(player -> player.numberOfCards() > 0)
                .collect(Collectors.toList());

        return winners;
    }

    ////Static Methods////
    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
