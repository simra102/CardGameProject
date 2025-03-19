package rummygame;

import java.util.*;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
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

    public boolean hasSet() {
        Map<String, Integer> rankCount = new HashMap<>();
        Map<String, List<Integer>> suitToRanks = new HashMap<>();

        for (Card card : hand.getCards()) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);

            int rankValue = getRankValue(card.getRank());
            suitToRanks.putIfAbsent(card.getSuit(), new ArrayList<>());
            suitToRanks.get(card.getSuit()).add(rankValue);
        }

        for (int count : rankCount.values()) {
            if (count >= 3) {
                return true;
            }
        }

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
