import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;

    public Game() {
        deck = new Deck();
        deck.shuffle();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    public void startGame() {
        System.out.println("Game Started! Dealing cards...");

        // Each player gets 5 cards
        for (int i = 0; i < 5; i++) {
            player1.receiveCard(deck.dealCard());
            player2.receiveCard(deck.dealCard());
        }

        player1.showHand();
        player2.showHand();

        System.out.println("Game Over!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game!");
        System.out.println("Press Enter to start...");
        scanner.nextLine();

        Game game = new Game();
        game.startGame();

        scanner.close();
    }
}

