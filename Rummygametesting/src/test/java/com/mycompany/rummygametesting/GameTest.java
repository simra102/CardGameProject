package com.mycompany.rummygametesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        // Reset the singleton deck instance before each test
        Deck.resetInstance(); // ✅ Make sure your Deck class has a resetInstance() for clean tests
        List<String> playerNames = Arrays.asList("Alice", "Bob");
        game = new Game(playerNames);
    }

    @Test
    public void testDeckInitialization() {
        Deck deck = game.getDeck();
        int expectedDeckSize = 52 - (2 * 7); // 2 players * 7 cards dealt
        assertEquals(expectedDeckSize, deck.remainingCards(), "Deck should have 38 cards after dealing.");
    }

    @Test
    public void testDrawCard() {
        Player current = game.getCurrentPlayer();
        int initialHandSize = current.getHand().getCards().size();
        game.drawCardForCurrentPlayer();
        assertEquals(initialHandSize + 1, current.getHand().getCards().size(), "Hand should increase by 1 after drawing.");
    }

    @Test
    public void testDiscardCard() {
        Player current = game.getCurrentPlayer();
        game.drawCardForCurrentPlayer(); // ensure one extra card to discard
        int initialHandSize = current.getHand().getCards().size();

        game.discardCardByIndex(0); // discard first card
        assertEquals(initialHandSize - 1, current.getHand().getCards().size(), "Hand should decrease by 1 after discarding.");
  
    }

    @Test
    public void testPlayerTurnSwitching() {
        Player first = game.getCurrentPlayer();
        game.nextTurn();
        Player second = game.getCurrentPlayer();
        assertNotEquals(first, second, "Turn should switch to next player.");
    }

    @Test
    public void testGameFlow() {
        Player current = game.getCurrentPlayer();
        int cardsBefore = current.getHand().getCards().size();
        game.drawCardForCurrentPlayer();
        game.discardCardByIndex(0);
        int cardsAfter = current.getHand().getCards().size();

        assertEquals(cardsBefore, cardsAfter, "After draw and discard, hand size should remain same.");
    }

  
}
