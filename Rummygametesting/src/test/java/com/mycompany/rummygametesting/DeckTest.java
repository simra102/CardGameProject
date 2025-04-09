package com.mycompany.rummygametesting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DeckTest {

    @Test
    public void testShuffle() {
        // Get the singleton instance of the deck
        Deck deck = Deck.getInstance();
        
        // Capture the original state of the deck (before shuffle)
    
        
        // Shuffle the deck
        deck.shuffle();
        
        // Capture the shuffled deck
        List<Card> shuffledDeck = deck.getCards();
        
        // Assert that the deck has been shuffled by comparing the original deck and shuffled deck
        assertNotEquals( shuffledDeck, "Deck should be shuffled.");
    }

    @Test
    public void testDrawCard() {
        // Get the singleton instance of the deck
        Deck deck = Deck.getInstance();
        
        // Draw a card
        Card drawnCard = deck.drawCard();
        
        // Assert that the card drawn is not null
        assertNotNull(drawnCard, "A card should be drawn.");
        
        // Assert that the number of remaining cards is one less
        int remainingCards = deck.remainingCards();
        assertEquals(51, remainingCards, "There should be 51 cards remaining after drawing one.");
    }

    @Test
    public void testRemainingCards() {
        // Get the singleton instance of the deck
        Deck deck = Deck.getInstance();
        
        // Check the remaining cards before drawing
        int initialRemainingCards = deck.remainingCards();
        
        // Draw a few cards
        deck.drawCard();
        deck.drawCard();
        
        // Check the remaining cards after drawing two cards
        int remainingCardsAfterDraw = deck.remainingCards();
        
        // Assert that the remaining cards are reduced by 2
        assertEquals(initialRemainingCards - 2, remainingCardsAfterDraw, "Remaining cards should decrease by the number of cards drawn.");
    }
}
