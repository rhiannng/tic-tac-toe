package ui;

import javax.swing.*;

//the dialog that pops up when you win
public class WinningGameDialog extends EndGameDialog {
    private static final String message = "You Won!";
    private static final String source = "./data/winGameSound.wav";

    //EFFECTS: creates a dialog that tells user they won and plays winGameSound.wav
    public WinningGameDialog(JFrame gameFrame) {
        super(gameFrame, message, source);
    }
}
