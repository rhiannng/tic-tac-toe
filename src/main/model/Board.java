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
        position1 = new Position("");
        position2 = new Position("");
        position3 = new Position("");
        position4 = new Position("");
        position5 = new Position("");
        position6 = new Position("");
        position7 = new Position("");
        position8 = new Position("");
        position9 = new Position("");
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
        if (playerMove.equals("1")) {
            position1.fillPositionX();
            availablePositions.remove(position1);
        } else if (playerMove.equals("2")) {
            position2.fillPositionX();
            availablePositions.remove(position2);
        } else if (playerMove.equals("3")) {
            position3.fillPositionX();
            availablePositions.remove(position3);
        } else if (playerMove.equals("4")) {
            position4.fillPositionX();
            availablePositions.remove(position4);
        } else if (playerMove.equals("5")) {
            position5.fillPositionX();
            availablePositions.remove(position5);
        } else if (playerMove.equals("6")) {
            position6.fillPositionX();
            availablePositions.remove(position6);
        } else if (playerMove.equals("7")) {
            position7.fillPositionX();
            availablePositions.remove(position7);
        } else if (playerMove.equals("8")) {
            position8.fillPositionX();
            availablePositions.remove(position8);
        } else if (playerMove.equals("9")) {
            position9.fillPositionX();
            availablePositions.remove(position9);
        }
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
