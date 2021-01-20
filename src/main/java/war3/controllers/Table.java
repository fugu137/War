/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war3.controllers;

import war3.domain.Card;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import war3.domain.Card;
import war3.domain.Player;

/**
 *
 * @author Michael
 */
public class Table {

    private HashMap<Player, Card> cards;
    private List<Card> bonusCards;

    public Table() {
        this.cards = new HashMap<>();
        this.bonusCards = new ArrayList<>();
    }

    public void add(Player player, Card card) {
        this.cards.put(player, card);
    }

    public Player findHighest() {
        int highVal = 0;
        Player highest = null;
        
        for (Player p : cards.keySet()) {
            if (cards.get(p).getValue() > highVal) {
                highVal = cards.get(p).getValue();
                highest = p;
            }
        }
        return highest;
    }

    public List<Player> jointWinners(Player highest) {

        List<Player> highList = new ArrayList<>();

        for (Player p : cards.keySet()) {
            if (cards.get(p).getValue() == cards.get(highest).getValue()) {
                highList.add(p);
            }
        }
        return highList;
    }

    public boolean checkIfTie(List<Player> highList) {
        return (highList.size() > 1);
    }

    public List<Card> pickUpCards() {
        List<Card> list = new ArrayList<>();

        for (Card c : this.cards.values()) {
            list.add(c);
        }
        this.cards.clear();

        for (Card c : this.bonusCards) {
            list.add(c);
        }
        this.bonusCards.clear();

        return list;
    }

    public void moveToBonusCards() {
        pickUpCards().forEach(card -> bonusCards.add(card));
    }

    public void addBonusCard(Card card) {
        this.bonusCards.add(card);
    }
    
}
