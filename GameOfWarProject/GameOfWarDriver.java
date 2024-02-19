package GameOfWarProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameOfWarDriver {

    public static void main(String[] args) {

        // make the deck of cards
        Deck deck = new Deck();

        ArrayList<String> suits = new ArrayList<String>(Arrays.asList("Hearts", "Diamonds", "Clubs", "Spades"));
        ArrayList<String> names = new ArrayList<String>(Arrays.asList("Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"));

        int count = 0;
        for (int i=0; i<suits.size(); i++) {
            for (int j=2; j<15; j++) {
                deck.addCard(new Card(suits.get(i), j, names.get(count)));
                count += 1;
            }
            count = 0;
        }

        // create the players
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the first player: ");
        Player player1 = new Player(scan.next());
        System.out.println("Enter the name of the second player: ");
        Player player2 = new Player(scan.next());

        // shuffle the deck
        deck.shuffle();

        // deals the cards to the players
        // deals 26 cards to player 1
        for (int i=0; i<26; i++) {
            player1.addCard(deck.getDeck().get(i));
        }
        // deals 26 cards to player 2
        for (int i=26; i<52; i++) {
            player2.addCard(deck.getDeck().get(i));
        }
        // for testing
//        System.out.println(player1.getHand().size());
//        System.out.println(player2.getHand().size());

        // play the game
        // both players play one card onto the table
        // check which card has a higher value
        // whichever player played the higher card, deposit the cards from the table into the winners hand
        // if a tie happens:
            // deposit four cards from each player onto the table
            // check the last card deposited from each player, and compare them
            // whoever has the higher card, gets all the cards on the table

        ArrayList<Card> table = new ArrayList<Card>();
        while (player1.hasCards() && player2.hasCards()) {

            // for testing
            System.out.println(player1.getName() +  " has " + player1.getHand().size() + " cards.");
            System.out.println(player2.getName() +  " has " + player2.getHand().size() + " cards.");

            System.out.println(player1.getName() + " plays the " + player1.getHand().get(0).getName());
            System.out.println(player2.getName() + " plays the " + player2.getHand().get(0).getName());

            // player 1 wins the turn
            if (player1.getHand().get(0).getValue() > player2.getHand().get(0).getValue()) {
                // move player 2's card into the back of player 1's hand
                System.out.println(player1.getName() + " wins this turn.");
                player1.addCard(player2.getHand().get(0));
                // so that the card player 1 played gets moved to the back of their hand
                Card temp = player1.getHand().get(0);
                player1.takeTurn(player1.getHand().get(0));
                player1.addCard(temp);

                player2.takeTurn(player2.getHand().get(0));

            }
            // player 2 wins the turn
            else if (player1.getHand().get(0).getValue() < player2.getHand().get(0).getValue()) {
                // move player 1's card into the back of player 2's hand
                System.out.println(player2.getName() + " wins this turn.");
                player2.addCard(player1.getHand().get(0));

                Card temp = player2.getHand().get(0);
                player2.takeTurn(player2.getHand().get(0));
                player2.addCard(temp);

                player1.takeTurn(player1.getHand().get(0));

            }

            // there is a tie -----------------------------
            else {
                // indicator that a tie happened
                System.out.println("----------I declare war!----------");
                // contents in else conditional only work if the players have more than 4 cards
                // in the case that a war happens close to the end of the game
                    // meaning a player has less than 4 cards
                // the war is different
                if (player1.getHand().size() < 4 || player2.getHand().size() < 4){
                    // indicator that a tie happened at the end of the game
                    System.out.println("I declare war! ( < 4 )");
                    if (player1.getHand().size() < 4) { // this would mean player 1 loses the game

                        if (player1.getHand().get(player1.getHand().size() - 1).getValue() < player2.getHand().get(player1.getHand().size() - 1).getValue()) {
                            for (int i = 0; i <= player1.getHand().size() - 1; i++) {
                                player2.addCard(player1.getHand().get(0));
                                player1.takeTurn(player1.getHand().get(0));
                            }
                        }
                        // player 1 wins the war, and the game goes on
                        else {
                            // something is wrong here?
                            // I think it's when the player has only 1 card, the for loop doesn't work because it only goes through once?
                            for (int i = 0; i <= player1.getHand().size() - 1; i++){
                                player1.addCard(player2.getHand().get(0));
                                player2.takeTurn(player2.getHand().get(0));
                            }

                        }
                    }
                    else { // if player 2's hand is less than 4
                        // player 2 loses
                        if (player2.getHand().get(player2.getHand().size() - 1).getValue() < player1.getHand().get(player2.getHand().size() - 1).getValue()) {
                            for (int i = 0; i <= player2.getHand().size() - 1; i++) {
                                player1.addCard(player2.getHand().get(0));
                                player2.takeTurn(player2.getHand().get(0));
                            }
                        }
                        else {
                            // something is wrong here?
                            for (int i = 0; i <= player2.getHand().size() - 1; i++){
                                player2.addCard(player1.getHand().get(0));
                                player1.takeTurn(player1.getHand().get(0));
                            }


                        }

                    }
                }
                // if a war happens at the start or in the middle (meaning a player has more than 4 cards)
                else {
                    // need a while loop in case there is a double, triple, etc. war
                    boolean tie = true;
                    while(tie) {

                        // adds 4 cards from each hand onto the table
                        for (int i = 0; i < 4; i++) {
                            table.add(player1.getHand().get(0));
                            table.add(player2.getHand().get(0));
                            player1.takeTurn(player1.getHand().get(0));
                            player2.takeTurn(player2.getHand().get(0));
                        }
                        // System.out.println(table.size()); // for testing
                        // check the index 0 of each player to see which player won
                        // also set tie variable to false
                        // if tie go again
                        if (table.get(table.size() - 1).getValue() > table.get(table.size() - 2).getValue()) {
                            // player 2's face up card is in the -1 index spot
                            // player 1's face up card is in the -2 spot
                            // for this if statement, it is true if player 2 wins
                            System.out.println(player2.getName() + " wins this war.");
                            tie = false; // there was no tie, meaning no additional war
                            // add all the cards on the table to player 2's hand
                            for (int i = 0; i <= table.size() - 1; i++) {
                                player2.addCard(table.get(i));
                            }
                            table.clear(); // clears the table

                        } else if (table.get(table.size() - 1).getValue() < table.get(table.size() - 2).getValue()) {
                            // for this if statement, it is true if player 1 wins
                            System.out.println(player1.getName() + " wins this war.");
                            tie = false; // there was no tie, meaning no additional war
                            // add all the cards on the table to player 1's hand
                            for (int i = 0; i <= table.size() - 1; i++) {
                                player1.addCard(table.get(i));
                            }
                            table.clear(); // clears the table
                        }
                        else {
                            // if there happens to be a war within a war, the process goes again
                            tie = true;

                        }

                    }

                }

            }

        }

        // check which player has an empty hand, and print the opposite players hand
        if (player1.hasCards()) {
            System.out.println(player1.getName() + " wins the game!");
        }
        else {
            System.out.println(player2.getName() + " wins the game!");
        }

    }

}



// prints out all the cards in the deck
//        for (int i=0; i<deck.getDeck().size(); i++) {
//            System.out.println(deck.getDeck().get(i).getName());
//        }