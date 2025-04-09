package com.mycompany.rummygametesting;

import java.util.*;

/**
 * The Player class represents a player in the Rummy game.
 * 
 * 🎯 DESIGN PATTERNS & PRINCIPLES USED:
 * - **Single Responsibility Principle (SRP)**: This class only manages player data and actions.
 * - **Object Composition**: Uses a `Hand` object to manage cards (HAS-A relationship).
 * - **Encapsulation**: Fields are private with public accessors.
 */
public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();  // Composition
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean removeCard(Card card) {
        return hand.removeCard(card);
    }

    public void showHand() {
        System.out.println(name + "'s hand: " + hand.getCards());
    }

    /**
     * Checks if player has a winning set (3+ cards of same rank or 3+ sequence).
     * This logic is eligible for use with the Strategy Pattern.
     */
    public boolean hasSet() {
        Map<String, Integer> rankCount = new HashMap<>();
        Map<String, List<Integer>> suitToRanks = new HashMap<>();

        // Count ranks and prepare for sequence check
        for (Card card : hand.getCards()) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);

            int rankValue = getRankValue(card.getRank());
            suitToRanks.putIfAbsent(card.getSuit(), new ArrayList<>());
            suitToRanks.get(card.getSuit()).add(rankValue);
        }

        // Check for 3+ cards of same rank (e.g. 3 Kings)
        for (int count : rankCount.values()) {
            if (count >= 3) {
                return true;
            }
        }

        // Check for sequence of 3+ in same suit (e.g. 5♥, 6♥, 7♥)
        for (List<Integer> ranks : suitToRanks.values()) {
            Collections.sort(ranks);
            int consecutiveCount = 1;
            for (int i = 1; i < ranks.size(); i++) {
                if (ranks.get(i) == ranks.get(i - 1) + 1) {
                    consecutiveCount++;
                    if (consecutiveCount >= 3) {
                        return true;
                    }
                } else {
                    consecutiveCount = 1;
                }
            }
        }

        return false;
    }

    private int getRankValue(String rank) {
        switch (rank) {
            case "A": return 1;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            default: return Integer.parseInt(rank);
        }
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
