package model;

//represents a specific position in the board, with a symbol and a positionNumber
public class Position {
    String symbol;
    String positionNumber;

    //MODIFIES: this
    //EFFECTS : instantiates a Position of symbol s and positionNumber i
    public Position(String s, String i) {
        this.symbol = s;
        this.positionNumber = i;
    }

    //MODIFIES: symbol
    //EFFECTS: changes the symbol string to "X"
    public void fillPositionWithX() {
        this.symbol = "X";
    }

    //MODIFIES: symbol
    //EFFECTS: changes the symbol string to "O"
    public void fillPositionWithO() {
        this.symbol = "O";
    }

}
