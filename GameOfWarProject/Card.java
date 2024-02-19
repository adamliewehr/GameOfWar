package GameOfWarProject;

public class Card {

    private String suit;
    private int value;
    private String name;
    private String fullName;

    public Card(String s, int v, String n) {
        suit = s;
        value = v;
        name = n;
        fullName = n + "Of" + s;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return fullName;
    }

}
