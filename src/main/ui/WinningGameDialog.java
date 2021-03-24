package ui;

import javax.swing.*;

public class WinningGameDialog extends EndGameDialog {
    private static final String message = "You Won!";
    private static final String source = "./data/winGameSound.wav";

    public WinningGameDialog(JFrame gameFrame) {
        super(gameFrame, message, source);
    }
}
