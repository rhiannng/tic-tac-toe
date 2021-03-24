package ui;

import javax.swing.*;

public class TyingGameDialog extends EndGameDialog {
    private static final String message = "We Tied!";
    private static final String source = "./data/tieGameSound.wav";

    public TyingGameDialog(JFrame gameFrame) {
        super(gameFrame, message, source);
    }
}
