package ui;

import model.Board;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//represents the panel that has the game board with all the positions
public class BoardPanel extends JPanel {
    Game game;
    Board board;
    ArrayList<PositionButton> positionButtonList;
    GridBagConstraints constraints;

    //EFFECTS: constructs a BoardPanel and sets up all the PositionButtons in the panel
    public BoardPanel(Board b, Game g) {
        super(new GridBagLayout());
        this.board = b;
        this.game = g;
        this.positionButtonList = new ArrayList<>();
        this.constraints = new GridBagConstraints();
        List<Position> allPositionsList = new ArrayList<>(b.getAllPositions().values());

        for (Position p : allPositionsList) {
            PositionButton posButton = new PositionButton(p, b, this);
            constraints.gridx = posButton.getColumn();
            constraints.gridy = posButton.getRow();
            positionButtonList.add(posButton);
            add(posButton,constraints);
        }
    }

    //MODIFIES: board, posButton
    //EFFECTS: lets the computer make a move and displays the move onto the corresponding PositionButton
    public void displayComputerMove() {
        Position pos = board.moveAgainstPlayer();
        for (PositionButton posButton : positionButtonList) {
            if (posButton.getPosition().equals(pos)) {
                posButton.setText(pos.getSymbol());
            }
        }
    }

    //EFFECTS: once player has made a move, checks if board has an end-game scenario, otherwise, allows computer to
    //         make a move (also checks if computer move creates an end-game scenario)
    public void initiateNextMove() {
        if (board.checkEndGame()) {
            game.endGameSequence();
        } else {
            displayComputerMove();
            if (board.checkEndGame()) {
                game.endGameSequence();
            }
        }
    }
}
