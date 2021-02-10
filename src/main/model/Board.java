package model;

import java.util.ArrayList;
import java.util.Random;

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
    ArrayList<Position> availablePositions;

    //MODIFIES: position1-9, availablePositions
    //EFFECTS: instantiates position1-9 & availablePositions, adds position1-9 to availablePositions
    public Board() {
        position1 = new Position("","1");
        position2 = new Position("","2");
        position3 = new Position("","3");
        position4 = new Position("","4");
        position5 = new Position("","5");
        position6 = new Position("","6");
        position7 = new Position("","7");
        position8 = new Position("","8");
        position9 = new Position("","9");
        availablePositions = new ArrayList<>();
        availablePositions.add(position1);
        availablePositions.add(position2);
        availablePositions.add(position3);
        availablePositions.add(position4);
        availablePositions.add(position5);
        availablePositions.add(position6);
        availablePositions.add(position7);
        availablePositions.add(position8);
        availablePositions.add(position9);
    }

    //REQUIRES: the move is available and valid
    //MODIFIES: position1-9, availablePositions
    //EFFECTS : marks the specified position with "X", removes position from availablePositions
    public void playerMakesAMove(String playerMove) {
        Position nextPosition;
        nextPosition = stringToPosition(playerMove);
        nextPosition.fillPositionX();
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
        nextPosition.fillPositionO();
        availablePositions.remove(nextPosition);
    }

    // EFFECTS: returns a random Position from the availablePositions list
    public Position getRandomAvailablePosition() {
        Random r = new Random();
        int i = r.nextInt(availablePositions.size());
        return availablePositions.get(i);
    }

}
