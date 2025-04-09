package com.mycompany.rummygametesting;

import java.util.*;

// Singleton Pattern applied: only one deck can exist
public class Deck {
    private static Deck instance; // static instance
    private List<Card> cards;
    private Random rand;

    // private constructor
    private Deck() {
        cards = new ArrayList<>();
        rand = new Random();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Static method to get the single instance
    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public int remainingCards() {
        return cards.size();
    }

    // Add a method to get the list of cards
    public List<Card> getCards() {
        return cards;
    }
    public static void resetInstance() {
    instance = null;
}
}
