/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.rummygametesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testThreeOfAKind() {
        Player player = new Player("Arsh");
        player.addCard(new Card("9", "Hearts"));
        player.addCard(new Card("9", "Spades"));
        player.addCard(new Card("9", "Clubs"));

        assertTrue(player.hasSet(), "Should detect three of a kind.");
    }

    @Test
    public void testSequence() {
        Player player = new Player("Simran");
        player.addCard(new Card("4", "Diamonds"));
        player.addCard(new Card("5", "Diamonds"));
        player.addCard(new Card("6", "Diamonds"));

        assertTrue(player.hasSet(), "Should detect a sequence.");
    }

    @Test
    public void testNoSet() {
        Player player = new Player("Kriss");
        player.addCard(new Card("2", "Clubs"));
        player.addCard(new Card("7", "Hearts"));
        player.addCard(new Card("J", "Spades"));

        assertFalse(player.hasSet(), "Should NOT detect any set.");
    }
}
