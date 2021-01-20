/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war3;

import war3.controllers.Game;

/**
 *
 * @author Michael
 */
public class Main {

    public static void main(String[] args) {

        String standardDeck = "standardDeck.csv";

        play(standardDeck);


    }

    
    
    public static void play(String deckPath) {
        Game game = new Game(deckPath);

        game.populateDeck();
        
        while (true) {
            game.setup();
            if (game.playersAreReady()) {
                break;
            }
        }
        game.shuffleAndDeal();
        
        while (game.playersHaveCards()) {
            game.playRound();
        }
        game.podium();
        
    }
}
