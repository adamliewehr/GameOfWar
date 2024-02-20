package GameOfWarProject;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand = new ArrayList<Card>();

    private String name;

    public Player(String n) {
        hand = new ArrayList<Card>();
        name = n;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    // get the size of the hand
    public int getSize() {
        return hand.size();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void takeTurn(Card card) {
        hand.remove(card);
    }

    public boolean hasCards() {
        return hand.size() > 0;
    }

    public String getName() {
        return name;
    }

}
