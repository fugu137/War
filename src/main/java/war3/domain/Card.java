package war3.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class Card {
    
    private CardType type;
    private Suit suit;
    private int value;

    public Card(CardType type, Suit suit) {
        this.type = type;
        this.suit = suit;
        this.value = type.ordinal();
    }

     public CardType getType() {
        return type;
    }
     
    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + " of " + suit;
    }
    
}
