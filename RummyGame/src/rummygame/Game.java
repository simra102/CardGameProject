package rummygame;

import java.util.*;

public class Game {
    private List<Player> players;
    private Deck deck;
    private Discard discardPile;
    private int currentPlayerIndex;
    private Scanner scanner;

    public Game(List<String> playerNames) {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.discardPile = new Discard();
        this.currentPlayerIndex = 0;
        this.scanner = new Scanner(System.in);

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

    public void startGame() {
        boolean gameInProgress = true;

        while (gameInProgress) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn:");
            currentPlayer.showHand();

            // Draw a card from deck
            Card drawnCard = deck.drawCard();
            currentPlayer.addCard(drawnCard);
            System.out.println(currentPlayer.getName() + " drew a " + drawnCard);

            // Player decides whether to discard
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
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
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

            // Check if the player has a set or sequence
            if (currentPlayer.hasSet()) {
                System.out.println(currentPlayer.getName() + " wins!");
                gameInProgress = false;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }
}
