package war3.domain;

import war3.domain.Hand;
import war3.domain.Card;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */
public class Player {

    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }
    
    public int numberOfCards() {
        return this.hand.size();
    }
    
    public void addToHand(Card card) {
        this.hand.add(card);
    }

    public Card flip() {
        return hand.draw();
    }

    @Override
    public String toString() {
        return this.name + " (number of cards: " + this.numberOfCards() + ")";
    }
}
