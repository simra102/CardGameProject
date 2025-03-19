package rummygame;

import java.util.*;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void showHand() {
        System.out.println("Hand: " + cards);
    }
}
