package ui;

import javax.swing.*;

//the dialog that pops up when you tie
public class TyingGameDialog extends EndGameDialog {
    private static final String message = "We Tied!";
    private static final String source = "./data/tieGameSound.wav";

    //EFFECTS: creates a dialog that tells user they tied and plays tieGameSound.wav
    public TyingGameDialog(JFrame gameFrame) {
        super(gameFrame, message, source);
    }
}
