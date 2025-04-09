package com.mycompany.rummygametesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HandTest {

    private Hand hand;
    private Card card1;
    private Card card2;

    @BeforeEach
    public void setUp() {
        hand = new Hand();
        card1 = new Card("Hearts", "7");
        card2 = new Card("Clubs", "Ace");
    }

    @Test
    public void testAddCard() {
        hand.addCard(card1);
        hand.addCard(card2);

        List<Card> cards = hand.getCards();
        assertTrue(cards.contains(card1), "Hand should contain the first added card.");
        assertTrue(cards.contains(card2), "Hand should contain the second added card.");
        assertEquals(2, cards.size(), "Hand should contain exactly 2 cards.");
    }

    @Test
    public void testRemoveCard() {
        hand.addCard(card1);
        boolean removed = hand.removeCard(card1);

        assertTrue(removed, "Card should be removed successfully.");
        assertFalse(hand.getCards().contains(card1), "Card should no longer be in hand.");
    }

    @Test
    public void testRemoveCard_NotInHand() {
        boolean removed = hand.removeCard(card2);

        assertFalse(removed, "Trying to remove a card not in hand should return false.");
    }

    @Test
    public void testGetCardsReturnsList() {
        hand.addCard(card1);
        List<Card> cards = hand.getCards();

        assertNotNull(cards, "getCards() should return a non-null list.");
        assertEquals(1, cards.size(), "getCards() should return correct size.");
    }

    @Test
    public void testShowHand() {
        hand.addCard(card1);
        hand.addCard(card2);
        System.out.println("Expected hand printout below:");
        hand.showHand(); // Visual verification during test run
    }
}
