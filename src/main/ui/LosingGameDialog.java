package ui;

import javax.swing.*;

//the dialog that pops up when you lose
public class LosingGameDialog extends EndGameDialog {
    private static final String message = "You Lost!";
    private static final String source = "./data/loseGameSound.wav";

    //EFFECTS: creates a dialog that tells user they lost and plays loseGameSound.wav
    public LosingGameDialog(JFrame gameFrame) {
        super(gameFrame, message, source);
    }
}
