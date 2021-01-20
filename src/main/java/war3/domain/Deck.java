package war3.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }
    
    public Card draw() {
        Card card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public int size() {
        return this.cards.size();
    }
    
    public boolean hasCards() {
        return (cards.size() > 0);
    }
    
    public List<Card> cards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Card c : cards) {
            string.append(c);
            string.append("\n");
        }
        return string.toString() + "Number of cards: " + this.size() + "\n";
    }
}
