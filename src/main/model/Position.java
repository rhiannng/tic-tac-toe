package model;

import org.json.JSONObject;

//represents a specific position in the board, with a symbol and a positionNumber
//TODO citation: code taken and modified from Thingy.java package in JsonSerializationDemo
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

    //EFFECTS: returns a JSONObject of this position
    public JSONObject positionToJson() {
        JSONObject json = new JSONObject();
        json.put("symbol", symbol);
        json.put("positionNumber", positionNumber);
        return json;
    }

    //EFFECTS: returns position's symbol (getter)
    public String getSymbol() {
        return symbol;
    }

    //EFFECTS: returns position's positionNumber (getter)
    public String getPositionNumber() {
        return positionNumber;
    }
}
