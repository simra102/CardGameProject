package rummygame;

import java.util.*;

public class Discard {
    private List<Card> discardPile;

    public Discard() {
        discardPile = new ArrayList<>();
    }

    public void discard(Card card) {
        discardPile.add(card);
    }

    public Card drawFromDiscard() {
        if (discardPile.isEmpty()) {
            return null;
        }
        return discardPile.remove(discardPile.size() - 1);
    }

    public void showDiscardPile() {
        System.out.println("Discard pile: " + discardPile);
    }
}
