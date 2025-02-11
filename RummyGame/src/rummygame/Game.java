/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rummygame;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Game class for Rummy
 */
public class Game {
    private final List<Player> players;
    private final Deck deck;
    private final Discard discard; // ✅ Using Discard class
    private final Scanner scanner;

    public Game(List<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        deck = new Deck();
        discard = new Discard(); // ✅ Correct class name
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        // Deal initial cards (7 cards per player)
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            }
        }

        System.out.println("\nGame has started!\n");
        playTurns();
    }

    private void playTurns() {
        boolean gameOn = true;
        
        while (gameOn) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn.");
                player.showHand();

                // ✅ FIXED: Safe input handling
                int choice;
                while (true) {
                    System.out.println("Draw from (1) Deck or (2) Discard Pile? ");
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (choice == 1 || choice == 2) {
                            break; // Valid input, exit loop
                        } else {
                            System.out.println("Invalid choice! Enter 1 for Deck or 2 for Discard Pile.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a number (1 or 2).");
                        scanner.next(); // Clear invalid input
                    }
                }

                // Drawing a card
                Card drawnCard;
                if (choice == 1) { // Draw from deck
                    drawnCard = deck.drawCard();
                    System.out.println(player.getName() + " drew " + drawnCard);
                } else { // Draw from discard pile
                    drawnCard = discard.pickTopCard();
                    if (drawnCard == null) {
                        System.out.println("Discard pile is empty! Drawing from deck instead.");
                        drawnCard = deck.drawCard();
                    } else {
                        System.out.println(player.getName() + " picked up " + drawnCard + " from the discard pile.");
                    }
                }

                player.addCard(drawnCard);
                player.showHand();

                // Player chooses a card to discard
                System.out.println("Enter a card to discard (rank and suit, e.g., '10 Hearts'):");
                String discardInput = scanner.nextLine();
                String[] parts = discardInput.split(" ");

                if (parts.length != 2) {
                    System.out.println("Invalid input! Please enter in format: '10 Hearts'");
                    continue;
                }

                Card discardCard = player.findCard(parts[0], parts[1]); // Find the actual card in hand
                if (discardCard != null) {
                    boolean removed = player.removeCard(discardCard);
                    if (removed) {
                        discard.discard(discardCard); // ✅ Using correct discard object
                        System.out.println(player.getName() + " discarded " + discardCard);
                    } else {
                        System.out.println("Invalid discard! That card is not in your hand.");
                    }
                } else {
                    System.out.println("Invalid input! You don't have that card.");
                }

                // ✅ FIXED: Correct winner message
                if (checkForWinner(player)) {
                    System.out.println("\n🎉 " + player.getName() + " has won the game! 🎉");
                    gameOn = false;
                    break;
                }

                if (deck.isEmpty()) {
                    System.out.println("\nThe deck is empty! Game Over.");
                    gameOn = false;
                }
            }
        }
    }

    // Check if a player has a winning set
    private boolean checkForWinner(Player player) {
        return player.hasSet(); // Calls Player's hasSet() method to check for valid sets
    }
}
