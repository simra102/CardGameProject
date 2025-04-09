package com.mycompany.rummygametesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DiscardTest {

    private Discard discard;
    private Card card1;
    private Card card2;

    @BeforeEach
    public void setup() {
        // Setup a new Discard instance and some test cards
        discard = new Discard();
        card1 = new Card("3", "Hearts");
        card2 = new Card("A", "Spades");
    }

    @Test
    public void testDiscardCard() {
        // Discard a single card and verify it is added to the discard pile
        discard.discard(card1);
        
        List<Card> discardPile = discard.getDiscardPile();
        
        assertEquals(1, discardPile.size(), "Discard pile should contain 1 card.");
        assertTrue(discardPile.contains(card1), "Discard pile should contain the card discarded.");
    }

    @Test
    public void testDrawFromDiscard() {
        // Discard a card and then draw it back
        discard.discard(card1);
        Card drawnCard = discard.drawFromDiscard();

        assertEquals(card1, drawnCard, "Drawn card should be the same as the discarded card.");
        assertTrue(discard.getDiscardPile().isEmpty(), "Discard pile should be empty after drawing a card.");
    }

    @Test
    public void testDrawFromEmptyDiscard() {
        // Try to draw from an empty discard pile
        Card drawnCard = discard.drawFromDiscard();

        assertNull(drawnCard, "Drawing from an empty discard pile should return null.");
    }

    @Test
    public void testMultipleDiscardsAndDraws() {
        // Discard multiple cards and draw one
        discard.discard(card1);
        discard.discard(card2);

        Card drawnCard = discard.drawFromDiscard();
        
        assertEquals(card2, drawnCard, "The last discarded card should be drawn first.");
        assertEquals(1, discard.getDiscardPile().size(), "Discard pile should have 1 card after drawing one.");
    }

    @Test
    public void testShowDiscardPile() {
        // Discard cards and show the discard pile
        discard.discard(card1);
        discard.discard(card2);

        // Test that showDiscardPile prints the correct output
        // For now, you will manually verify the console output, as JUnit doesn't capture standard output.
        discard.showDiscardPile();  // This will print the discard pile to the console
    }
}
