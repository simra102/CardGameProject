/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rummygame;
import java.util.*;

/**
 * Represents a player in the Rummy game.
 */
public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Add a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Remove a card from the player's hand (returns true if successful)
    public boolean removeCard(Card card) {
        return hand.removeIf(c -> c.getRank().equalsIgnoreCase(card.getRank()) && 
                                  c.getSuit().equalsIgnoreCase(card.getSuit()));
    }

    // Show the player's hand
    public void showHand() {
        System.out.println(name + "'s hand: " + hand);
    }

    // Check if player has a valid set (3 or more cards of the same rank) or a sequence (3+ consecutive of the same suit)
    public boolean hasSet() {
        Map<String, Integer> rankCount = new HashMap<>();
        Map<String, List<Integer>> suitToRanks = new HashMap<>();

        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);

            // Convert rank to numerical value
            int rankValue = getRankValue(card.getRank());
            suitToRanks.putIfAbsent(card.getSuit(), new ArrayList<>());
            suitToRanks.get(card.getSuit()).add(rankValue);
        }

        // Check for a set (3 or more of the same rank)
        for (int count : rankCount.values()) {
            if (count >= 3) {
                return true; // Player has a valid set
            }
        }

        // Check for a sequence (3+ consecutive cards of the same suit)
        for (List<Integer> ranks : suitToRanks.values()) {
            Collections.sort(ranks);
            int consecutiveCount = 1;
            for (int i = 1; i < ranks.size(); i++) {
                if (ranks.get(i) == ranks.get(i - 1) + 1) {
                    consecutiveCount++;
                    if (consecutiveCount >= 3) {
                        return true; // Player has a valid sequence
                    }
                } else {
                    consecutiveCount = 1; // Reset counter
                }
            }
        }

        return false; // No valid set or sequence found
    }

    // Get the player's name
    public String getName() {
        return name;
    }

    // Find a card in the player's hand by rank and suit
    public Card findCard(String rank, String suit) {
        for (Card card : hand) {
            if (card.getRank().equalsIgnoreCase(rank) && card.getSuit().equalsIgnoreCase(suit)) {
                return card; // Return the matching card
            }
        }
        return null; // Card not found
    }

    // Convert face card ranks (J, Q, K, A) to numbers for sequence checking
    private int getRankValue(String rank) {
        switch (rank) {
            case "A": return 1;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            default: return Integer.parseInt(rank); // Convert number ranks directly
        }
    }

    // Get player's hand
    public List<Card> getHand() {
        return hand;
    }
}
