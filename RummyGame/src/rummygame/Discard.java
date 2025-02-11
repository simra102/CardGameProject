/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package rummygame;
import java.util.Stack;

/**
 * Manages the discard pile for the game.
 */
public class Discard {
    private final Stack<Card> pile;

    public Discard() {
        pile = new Stack<>();
    }

    /**
     * Adds a card to the discard pile.
     * @param card The card to discard.
     */
    public void discard(Card card) {
        pile.push(card);
    }

    /**
     * Picks up the top card from the discard pile.
     * @return The top discarded card, or null if the pile is empty.
     */
    public Card pickTopCard() {
        return pile.isEmpty() ? null : pile.pop();
    }

    /**
     * Checks if the discard pile is empty.
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return pile.isEmpty();
    }
}
