/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war3.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Michael
 */
public enum Suit {
    DIAMONDS,
    CLUBS,
    HEARTS,
    SPADES;
    
    public static List<Suit> list() {
        return new ArrayList<>(Arrays.asList(DIAMONDS, CLUBS, HEARTS, SPADES));
    }
}
