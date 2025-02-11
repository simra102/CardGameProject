/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rummygame;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class Hand {

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void showHand() {
        System.out.println("Hand: " + cards);
    }
}

