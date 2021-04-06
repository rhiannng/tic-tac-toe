package model;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.*;

//represents a tic-tac-toe board with corresponding positions
public class Board {
    Position position1;
    Position position2;
    Position position3;
    Position position4;
    Position position5;
    Position position6;
    Position position7;
    Position position8;
    Position position9;
    HashMap<String, Position> allPositions;
    HashMap<String, Position> availablePositions;

    //MODIFIES: position1-9, availablePositions, allPositions
    //EFFECTS : creates position1-9 and adds them to availablePositions & allPositions which are also instantiated
    public Board() {
        position1 = new Position(" ","1");
        position2 = new Position(" ","2");
        position3 = new Position(" ","3");
        position4 = new Position(" ","4");
        position5 = new Position(" ","5");
        position6 = new Position(" ","6");
        position7 = new Position(" ","7");
        position8 = new Position(" ","8");
        position9 = new Position(" ","9");

        allPositions = new HashMap<>();
        setUpList(allPositions);

        availablePositions = new HashMap<>();
        setUpList(availablePositions);
    }

    //MODIFIES: list
    //EFFECTS: adds all the positions in the board to the list
    public void setUpList(HashMap<String, Position> list) {
        list.put("1", position1);
        list.put("2", position2);
        list.put("3", position3);
        list.put("4", position4);
        list.put("5", position5);
        list.put("6", position6);
        list.put("7", position7);
        list.put("8", position8);
        list.put("9", position9);
    }

    //REQUIRES: the move is available and valid
    //MODIFIES: position1-9, availablePositions
    //EFFECTS : marks the specified position with "X", removes position from availablePositions
    public void playerMakesAMove(String playerMove) {
        Position nextPosition;
        nextPosition = stringToPosition(playerMove);
        nextPosition.fillPositionWithX();
        availablePositions.remove(playerMove);
    }

    //REQUIRES: the move is available and valid
    //MODIFIES: position1-9, availablePositions
    //EFFECTS : marks the specified position with "X", removes position from availablePositions
    public void playerMakesAMove(Position p) {
        p.fillPositionWithX();
        availablePositions.remove(p.getPositionNumber());
    }

    //REQUIRES: the move is available and valid
    //MODIFIES: position1-9, availablePositions
    //EFFECTS : marks the specified position with "O", removes position from availablePositions (strictly for testing)
    public void otherPlayerMakesAMove(String move) {
        Position nextPosition;
        nextPosition = stringToPosition(move);
        nextPosition.fillPositionWithO();
        availablePositions.remove(move);
    }

    //REQUIRES: string corresponds to an available position's positionNumber
    //EFFECTS: returns Position with corresponding positionNumber
    public Position stringToPosition(String s) {
        return availablePositions.get(s);
    }

    //EFFECTS:  returns true if the move corresponds to a position
    public boolean isAValidMove(String s) {
        boolean b = false;
        for (int i = 1; i < 10; i++) {
            String str = Integer.toString(i);
            if (str.equals(s)) {
                i = 10;
                b = true;
            }
        }
        return b;
    }

    //REQUIRES: the move is a valid move
    //EFFECTS : returns true if the move corresponds to a position is in availablePositions
    public boolean isAnAvailableMove(String s) {
        boolean b = false;
        if (!(availablePositions.get(s) == null)) {
            b = true;
        }
        return b;
    }

    //MODIFIES: nextPosition, position1-9, availablePositions
    //EFFECTS: marks the specified position with "O", removes position from availablePositions
    public Position moveAgainstPlayer() {
        Position nextPosition;
        nextPosition = getRandomAvailablePosition();
        nextPosition.fillPositionWithO();
        availablePositions.remove(nextPosition.getPositionNumber());
        return nextPosition;
    }

    // EFFECTS: returns a random Position from the availablePositions list
    public Position getRandomAvailablePosition() {
        List<Position> availablePositionsList = new ArrayList<>(availablePositions.values());
        Random r = new Random();
        int i = r.nextInt(availablePositionsList.size());
        return availablePositionsList.get(i);
    }

    //EFFECTS : returns the symbol of the Position with the given positionNumber
    public String getPositionSymbol(String posNum) {
        Position p = allPositions.get(posNum);
        return p.getSymbol();
    }

    //EFFECTS: returns true if there is a tie or a win
    public boolean checkEndGame() {
        return checkTie() || checkWin("X") || checkWin("O");
    }

    //REQUIRES: sym is either "X" or "O"
    //EFFECTS: returns true if the given symbol has won
    public boolean checkWin(String sym) {
        boolean h = checkHorizontalWin(sym);
        boolean v = checkVerticalWin(sym);
        boolean d = checkDiagonalWin(sym);

        return h || v || d;
    }

    //EFFECTS: returns true if there are no more available positions left and no wins at all
    public boolean checkTie() {
        return availablePositions.isEmpty() && !checkWin("X") && !checkWin("O");
    }

    //REQUIRES: sym is either "X" or "O"
    //EFFECTS: returns true if positions1,2,3 or positions4,5,6 or positions7,8,9 have the given string as a symbol
    public boolean checkHorizontalWin(String sym) {
        boolean ans = false;

        if (allPositions.get("1").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("2").symbol, allPositions.get("3").symbol);
        } else if (allPositions.get("4").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("5").symbol, allPositions.get("6").symbol);
        } else if (allPositions.get("7").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("8").symbol, allPositions.get("9").symbol);
        }

        return ans;
    }

    //REQUIRES: sym is either "X" or "O"
    //EFFECTS: returns true if positions1,4,7 or positions2,5,8, or positions3,6,9 have the given string as a symbol
    public boolean checkVerticalWin(String sym) {
        boolean ans = false;

        if (allPositions.get("1").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("4").symbol, allPositions.get("7").symbol);
        } else if (allPositions.get("2").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("5").symbol, allPositions.get("8").symbol);
        } else if (allPositions.get("3").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("6").symbol, allPositions.get("9").symbol);
        }

        return ans;
    }

    //REQUIRES: sym is either "X" or "O"
    //EFFECTS: returns true if positions1,5,9 or positions3,5,9 have the given string as a symbol
    public boolean checkDiagonalWin(String sym) {
        boolean ans = false;

        if (allPositions.get("1").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("5").symbol, allPositions.get("9").symbol);
        } else if (allPositions.get("3").symbol.equals(sym)) {
            ans = checkSameSymbol(sym, allPositions.get("5").symbol, allPositions.get("7").symbol);
        }

        return ans;
    }

    //EFFECTS: returns true if the strings s1, s2, and s3 are the same
    boolean checkSameSymbol(String s1, String s2, String s3) {
        return s1.equals(s2) && s1.equals(s3);
    }

    public void setPosition1(Position p) {
        position1 = p;
    }

    public void setPosition2(Position p) {
        position2 = p;
    }

    public void setPosition3(Position p) {
        position3 = p;
    }

    public void setPosition4(Position p) {
        position4 = p;
    }

    public void setPosition5(Position p) {
        position5 = p;
    }

    public void setPosition6(Position p) {
        position6 = p;
    }

    public void setPosition7(Position p) {
        position7 = p;
    }

    public void setPosition8(Position p) {
        position8 = p;
    }

    public void setPosition9(Position p) {
        position9 = p;
    }

    public HashMap<String,Position> getAvailablePositions() {
        return availablePositions;
    }

    public HashMap<String,Position> getAllPositions() {
        return allPositions;
    }

    //MODIFIES: this
    //EFFECTS : clears the allPositions field, resetting and updating the field
    public void resetAllPositions() {
        allPositions = new HashMap<>();
        setUpList(allPositions);
    }

    //MODIFIES: this
    //EFFECTS : clears availablePosition field and loads in the available positions from the json file
    public void loadJsonAvailablePositions(ArrayList<Position> jsonList) {
        availablePositions = new HashMap<>();
        setUpList(availablePositions);
        boolean isThereAMatch;
        ArrayList<Position> list = new ArrayList<>(allPositions.values());

        for (Position p : list) {
            isThereAMatch = checkIfMatch(p, jsonList);
            if (!isThereAMatch) {
                availablePositions.remove(p.getPositionNumber());
            }
        }
    }

    //EFFECTS : returns true if the given Position has the same positionNumber as those in the list
    public boolean checkIfMatch(Position p, ArrayList<Position> list) {
        boolean b = false;

        for (Position jsonPosition : list) {
            if (p.positionNumber.equals(jsonPosition.positionNumber)) {
                b = true;
                break;
            }
        }
        return b;
    }

    //EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position1", position1.positionToJson());
        json.put("position2", position2.positionToJson());
        json.put("position3", position3.positionToJson());
        json.put("position4", position4.positionToJson());
        json.put("position5", position5.positionToJson());
        json.put("position6", position6.positionToJson());
        json.put("position7", position7.positionToJson());
        json.put("position8", position8.positionToJson());
        json.put("position9", position9.positionToJson());
        json.put("availablePositions", mapToJson(availablePositions));
        return json;
    }


    //EFFECTS: every position in the list is json-ified and put into a jsonArray, which is returned.
    public JSONArray mapToJson(HashMap<String, Position> map) {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Position> list = new ArrayList<>(map.values());
        for (Position p : list) {
            jsonArray.put(p.positionToJson());
        }
        return jsonArray;
    }

}
