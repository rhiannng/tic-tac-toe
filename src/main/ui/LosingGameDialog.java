package ui;

import javax.swing.*;

public class LosingGameDialog extends EndGameDialog {
    private static final String message = "You Lost!";
    private static final String source = "./data/loseGameSound.wav";

    public LosingGameDialog(JFrame gameFrame) {
        super(gameFrame, message, source);
    }
}
