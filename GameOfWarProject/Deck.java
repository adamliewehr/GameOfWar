package GameOfWarProject;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {

        deck = new ArrayList<Card>();

    }

    // get deck
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void addCard(Card card) {
            deck.add(card);
    }

    public void removeCard(Card card) {
        deck.remove(card);
    }

    // shuffle the deck
    public void shuffle() {
        for (int i=0; i<deck.size(); i++) { // goes through the loop 52 times
            int random = (int) (Math.random() * deck.size()); // creates a random variable from 0 to 51
            Card temp = deck.get(i); // stores the card at the current index in a temporary variable
            deck.set(i, deck.get(random)); // sets the card at the current index to the card at the random index
            deck.set(random, temp); // sets the card at the random index to the card that was at the current index
        }

    }

    // method for getting the size of the deck
    public int getSize() {
        return deck.size();
    }

}
