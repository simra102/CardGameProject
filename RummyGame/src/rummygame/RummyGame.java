package rummygame;

import java.util.*;

public class RummyGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Rummy!\n");

        // Create a game with two players
        Game game = new Game(Arrays.asList("Player 1", "Player 2"));

        // Start the game
        game.startGame();
    }
}
