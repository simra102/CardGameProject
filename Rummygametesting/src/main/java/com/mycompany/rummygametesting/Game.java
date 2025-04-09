package com.mycompany.rummygametesting;

import java.util.*;

/**
 * Game class handles the logic for playing the Rummy game.
 * 
 * - Uses Singleton Design Pattern for Deck
 * - Uses Strategy Design Pattern for flexible win conditions
 */
public class Game {
    private List<Player> players;
    private Deck deck; // Singleton deck
    private Discard discardPile;
    private int currentPlayerIndex;
    private Scanner scanner;
    private WinStrategy winStrategy; // Strategy Pattern

    /**
     * Initializes the game with given player names and prepares deck, players, and discard pile.
     * 
     * @param playerNames List of names of players
     */
    public Game(List<String> playerNames) {
        this.players = new ArrayList<>();
        this.deck = Deck.getInstance(); // Singleton Pattern applied
        this.discardPile = new Discard();
        this.currentPlayerIndex = 0;
        this.scanner = new Scanner(System.in);
        this.winStrategy = new SetWinStrategy(); // Strategy Pattern for win condition

        for (String name : playerNames) {
            players.add(new Player(name));
        }

        deck.shuffle();

        // Deal 7 cards to each player
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            }
        }
    }

    /**
     * Starts the main game loop, where each player takes turns until someone wins.
     */
    public void startGame() {
        boolean gameInProgress = true;

        while (gameInProgress) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn:");
            currentPlayer.showHand();

            // Draw a card from deck
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                currentPlayer.addCard(drawnCard);
                System.out.println(currentPlayer.getName() + " drew a " + drawnCard);
            } else {
                System.out.println("Deck is empty. Cannot draw a card.");
                break;
            }

            // Show discard pile and prompt discard
            discardPile.showDiscardPile();
            System.out.println("Enter card index to discard, or -1 to skip:");

            int discardIndex = -1;
            boolean validInput = false;
            while (!validInput) {
                try {
                    discardIndex = scanner.nextInt();
                    if (discardIndex == -1) {
                        validInput = true;
                    } else if (discardIndex >= 0 && discardIndex < currentPlayer.getHand().getCards().size()) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid index. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }

            if (discardIndex != -1) {
                Card cardToDiscard = currentPlayer.getHand().getCards().get(discardIndex);
                discardPile.discard(cardToDiscard);
                currentPlayer.removeCard(cardToDiscard);
                System.out.println(currentPlayer.getName() + " discarded " + cardToDiscard);
            } else {
                System.out.println(currentPlayer.getName() + " skipped discarding.");
            }

            // Check win using Strategy Pattern
            if (winStrategy.checkWin(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins!");
                gameInProgress = false;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }

    // ---------------------
    // 📦 Helper methods for test automation
    // ---------------------

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void drawCardForCurrentPlayer() {
        Card drawn = deck.drawCard();
        if (drawn != null) {
            getCurrentPlayer().addCard(drawn);
        }
    }

    public void discardCardByIndex(int index) {
        Player currentPlayer = getCurrentPlayer();
        if (index >= 0 && index < currentPlayer.getHand().getCards().size()) {
            Card toDiscard = currentPlayer.getHand().getCards().get(index);
            currentPlayer.removeCard(toDiscard);
            discardPile.discard(toDiscard);
        }
    }

    public boolean checkWinForCurrentPlayer() {
        return winStrategy.checkWin(getCurrentPlayer());
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public Deck getDeck() {
        return deck;
    }

    public Discard getDiscardPile() {
        return discardPile;
    }
}
