package model;

import java.util.ArrayList;
import java.util.Random;

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
    ArrayList<Position> allPositions;
    ArrayList<Position> availablePositions;

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

        allPositions = new ArrayList<>();
        setUpList(allPositions);

        availablePositions = new ArrayList<>();
        setUpList(availablePositions);
    }

    //MODIFIES: list
    //EFFECTS: adds all the positions in the board to the list
    public void setUpList(ArrayList<Position> list) {
        list.add(position1);
        list.add(position2);
        list.add(position3);
        list.add(position4);
        list.add(position5);
        list.add(position6);
        list.add(position7);
        list.add(position8);
        list.add(position9);
    }

    //REQUIRES: the move is available and valid
    //MODIFIES: position1-9, availablePositions
    //EFFECTS : marks the specified position with "X", removes position from availablePositions
    public void playerMakesAMove(String playerMove) {
        Position nextPosition;
        nextPosition = stringToPosition(playerMove);
        nextPosition.fillPositionWithX();
        availablePositions.remove(nextPosition);
    }

    //REQUIRES: string corresponds to an available position's positionNumber
    //EFFECTS: returns Position with corresponding positionNumber
    public Position stringToPosition(String s) {
        Position nextPos = new Position("","");
        for (int i = 0; i < availablePositions.size(); i++) {
            Position p = availablePositions.get(i);
            if (p.positionNumber.equals(s)) {
                nextPos = p;
                i = availablePositions.size();
            }
        }
        return nextPos;
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
        for (int i = 0; i < availablePositions.size(); i++) {
            Position p = availablePositions.get(i);
            if (p.positionNumber.equals(s)) {
                i = availablePositions.size();
                b = true;
            }
        }
        return b;
    }

    //MODIFIES: nextPosition, position1-9, availablePositions
    //EFFECTS: marks the specified position with "O", removes position from availablePositions
    public void moveAgainstPlayer() {
        Position nextPosition;
        nextPosition = getRandomAvailablePosition();
        nextPosition.fillPositionWithO();
        availablePositions.remove(nextPosition);
    }

    // EFFECTS: returns a random Position from the availablePositions list
    public Position getRandomAvailablePosition() {
        Random r = new Random();
        int i = r.nextInt(availablePositions.size());
        return availablePositions.get(i);
    }

    //EFFECTS : returns the symbol of the Position with the given positionNumber
    public String getPositionSymbol(String posNum) {
        String str = "";
        for (Position p : allPositions) {
            if (p.positionNumber.equals(posNum)) {
                str = p.symbol;
            }
        }
        return str;
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
        return availablePositions.size() == 0 && !checkWin("X") && !checkWin("O");
    }

    //REQUIRES: sym is either "X" or "O"
    //EFFECTS: returns true if positions1,2,3 or positions4,5,6 or positions7,8,9 have the given string as a symbol
    public boolean checkHorizontalWin(String sym) {
        boolean ans = false;

        if (position1.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position2.symbol, position3.symbol);
        } else if (position4.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position5.symbol, position6.symbol);
        } else if (position7.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position8.symbol, position9.symbol);
        }

        return ans;
    }

    //REQUIRES: sym is either "X" or "O"
    //EFFECTS: returns true if positions1,4,7 or positions2,5,8, or positions3,6,9 have the given string as a symbol
    public boolean checkVerticalWin(String sym) {
        boolean ans = false;

        if (position1.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position4.symbol, position7.symbol);
        } else if (position2.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position5.symbol, position8.symbol);
        } else if (position3.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position6.symbol, position9.symbol);
        }

        return ans;
    }

    //REQUIRES: sym is either "X" or "O"
    //EFFECTS: returns true if positions1,5,9 or positions3,5,9 have the given string as a symbol
    public boolean checkDiagonalWin(String sym) {
        boolean ans = false;

        if (position1.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position5.symbol, position9.symbol);
        } else if (position3.symbol.equals(sym)) {
            ans = checkSameSymbol(sym, position5.symbol, position7.symbol);
        }

        return ans;
    }

    //EFFECTS: returns true if the strings s1, s2, and s3 are the same
    boolean checkSameSymbol(String s1, String s2, String s3) {
        return s1.equals(s2) && s1.equals(s3);
    }
}
