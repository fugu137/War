/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war3.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import war3.domain.Card;
import war3.controllers.Game;
import war3.domain.Player;

/**
 *
 * @author Michael
 */
public class UserInterface {

    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    ////Prompts & Queries////
    public int menu() {

        while (true) {
            System.out.println("");
            System.out.println("Enter a number:");
            System.out.println("1. Start Game");
            System.out.println("2. View Deck");
            System.out.println("3. Quit");
            System.out.print("> ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                return 1;

            } else if (choice.equals("2")) {
                return 2;

            } else if (choice.equals("3")) {
                return 3;

            } else {
                System.out.println("Invalid input. Please select a number between 1 and 3.");
                System.out.println("");
            }
        }
    }

    public int queryNumberOfPlayers() {

        while (true) {
            System.out.print("Number of players (2-3): ");
            String input = scanner.nextLine();
            System.out.println("");

            if (input.matches("2|3")) {
                return Integer.valueOf(input);

            } else {
                System.out.println("Invalid number of players. Please try again.");
            }

        }
    }

    public List<String> queryPlayerNames(int numberOfPlayers) {
        List<String> playerNames = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter name of player " + (i + 1) + ": ");
            playerNames.add(scanner.nextLine());
        }
        System.out.println("");
        return playerNames;
    }

    public boolean queryReadyStatus() {
        System.out.println("Press ENTER to start the game, or type 'return' to go back to the main menu.");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("")) {
                return true;
            }

            if (input.matches("return|Return|RETURN")) {
                return false;
            }
        }
    }

    public void promptForReturnToMenu() {
        System.out.println("Press ENTER to return to main menu.");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("")) {
                break;
            }
        }
    }

    public boolean promptForCardFlip(Player player) {

        System.out.println("");
        System.out.println(player.getName() + ", press ENTER to turn over your card...");
        String input = scanner.nextLine();
        return (input.isEmpty());
    }

    public void waitForPlayerPress() {

        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
        }
    }

    public void warCountdown() {

        System.out.println("W...");
        waitForPlayerPress();

        System.out.println("A...");
        waitForPlayerPress();

        System.out.println("R...");
        waitForPlayerPress();

        System.out.println("SPELLS...");
        waitForPlayerPress();

        System.out.println("WAR!!!");
        System.out.println("");
        waitForPlayerPress();
    }

    ////Print Commands////
    public void printWelcomeMessage() {
        System.out.println("");
        System.out.println("==== WELCOME TO 'WAR', THE CARD GAME ====");
        System.out.println("");
    }

    public void printFarewellMessage() {

        System.out.println("");
        System.out.println("Exiting game. Thank you for playing!");
    }

    public void printVictoryMessage(Player player) {
        System.out.println("");

        Game.wait(800);

        System.out.println(player.getName() + "has won the game! Congratulations " + player.getName());
    }

    public void printNoVictoryMessage() {
        System.out.println("");

        Game.wait(800);

        System.out.println("No winner has been decided");
    }

    public void printPlayerList(List<Player> players) {
        System.out.println("=======Player Info=======");
        players.forEach(player -> System.out.println(player));
        System.out.println("");
    }

    public void printDealingMessage() {
        System.out.println("Dealing...");
        System.out.println("");
    }

    public void printShufflingMessage() {
        System.out.println("Shuffling...");
        System.out.println("");
    }

    public void printWinner(String winnerName) {

        System.out.println("");

        Game.wait(200);

        System.out.print(winnerName + " wins!");
    }

    public void printWinnings(List<Card> winnings) {

        StringBuilder string = new StringBuilder();

        for (int i = 0; i < winnings.size() - 1; i++) {
            string.append(winnings.get(i));
            string.append("\n");
        }
        string.append(winnings.get(winnings.size() - 1));

        System.out.println("");
        System.out.println("** Cards Won **");
        System.out.println(string.toString());
        System.out.println("");
    }

    public void printTieMessage(List<Player> highList) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < highList.size() - 1; i++) {
            string.append(highList.get(i).getName());
            string.append(" and ");
        }
        string.append(highList.get(highList.size() - 1).getName());

        System.out.println("");

        Game.wait(800);

        System.out.println("It's a tie between " + string.toString() + "!");
    }
}
