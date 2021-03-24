package ui;

import model.Board;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardPanel extends JPanel {
    Game game;
    Board board;
    ArrayList<PositionButton> positionButtonList;
    GridBagConstraints constraints;

    public BoardPanel(Board b, Game g) {
        super(new GridBagLayout());
        this.board = b;
        this.game = g;
        this.positionButtonList = new ArrayList<>();
        this.constraints = new GridBagConstraints();
        for (Position p : b.getAllPositions()) {
            PositionButton posButton = new PositionButton(p, b, this);
            constraints.gridx = posButton.getColumn();
            constraints.gridy = posButton.getRow();
            positionButtonList.add(posButton);
            add(posButton,constraints);
        }
    }

    //EFFECTS: returns positionButtonList (getter)
    public ArrayList<PositionButton> getPositionButtonList() {
        return positionButtonList;
    }

    public void displayComputerMove() {
        Position pos = board.moveAgainstPlayer();
        for (PositionButton posButton : positionButtonList) {
            if (posButton.getPosition().equals(pos)) {
                posButton.setText(pos.getSymbol());
            }
        }
    }

    public void initiateNextMove() {
        if (board.checkEndGame()) {
            game.endGameSequence();
        } else {
            displayComputerMove();
        }
    }
}
