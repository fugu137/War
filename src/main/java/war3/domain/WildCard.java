/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war3.domain;

import war3.domain.Card;
import war3.domain.CardType;

/**
 *
 * @author Michael
 */
public class WildCard extends Card {
    
    private WildSuit wildSuit;
    
    public WildCard(WildSuit wildSuit) {
        super(CardType.JOKER, null);
        this.wildSuit = wildSuit;
    }
    
    @Override
    public String toString() {
        return super.getType() + ", " + wildSuit;
    }
}
