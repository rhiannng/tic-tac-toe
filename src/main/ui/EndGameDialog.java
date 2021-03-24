package ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

//an abstract class for end game dialogue
public abstract class EndGameDialog extends JDialog {

    //EFFECTS: creates a new pop up that notifies user of end-game scenario, plays sound effect accordingly
    public EndGameDialog(JFrame gameFrame, String message, String source) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(source));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(gameFrame, message, "End Game Dialog", JOptionPane.PLAIN_MESSAGE);
    }
}
