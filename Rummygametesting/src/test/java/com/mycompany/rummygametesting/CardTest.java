/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.rummygametesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void testCardCreation() {
        Card card = new Card("A", "Spades");
        assertEquals("A", card.getRank());
        assertEquals("Spades", card.getSuit());
    }

    @Test
    public void testToString() {
        Card card = new Card("K", "Hearts");
        assertEquals("K of Hearts", card.toString());
    }
}
