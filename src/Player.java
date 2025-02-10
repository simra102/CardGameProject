import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void receiveCard(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }

    public void showHand() {
        System.out.println(name + "'s hand: " + hand);
    }

    public int getHandSize() {
        return hand.size();
    }
}

