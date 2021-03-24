package ui;

import model.Board;
import model.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PositionButton extends JButton {
    Position position;
    Board board;
    boolean isFilled;
    BoardPanel boardPanel;

    public PositionButton(Position p, Board b, BoardPanel boardPanel) {
        super(p.getSymbol());
        this.position = p;
        this.board = b;
        this.boardPanel = boardPanel;
        isFilled = !(p.getSymbol().equals(" "));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFilled = !(p.getSymbol().equals(" "));
                if (!isFilled) {
                    b.playerMakesAMove(p);
                    setText(p.getSymbol());
                    boardPanel.initiateNextMove();
                }
            }
        });
    }

    public int getRow() {
        String posNumString = position.getPositionNumber();
        int posNum = Integer.parseInt(posNumString);
        if (posNum <= 3) {
            return 0;
        } else if (posNum >= 7) {
            return 2;
        } else {
            return 1;
        }
    }

    public int getColumn() {
        String posNumString = position.getPositionNumber();
        int posNum = Integer.parseInt(posNumString);
        if ((posNum == 1) | (posNum == 4) | (posNum == 7)) {
            return 0;
        } else if ((posNum == 2) | (posNum == 5) | (posNum == 8)) {
            return 1;
        } else {
            return 2;
        }
    }

    public Position getPosition() {
        return position;
    }
}
