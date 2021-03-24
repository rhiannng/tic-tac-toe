package ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public abstract class EndGameDialog extends JDialog {
    private static final int WIDTH = 500;
    private static final int HEIGHT = WIDTH;

//    public EndGameDialog(JFrame gameFrame, String message, String source) {
//        JDialog dialog = new JDialog(gameFrame, "The game has ended");
//        JLabel label = new JLabel(message);
//        dialog.add(label);
//        dialog.setSize(WIDTH,HEIGHT);
//        dialog.setResizable(false);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        centreOnScreen();
////
////        Media media = new Media(new File(source).toURI().toString());
////        MediaPlayer mediaPlayer = new MediaPlayer(media);
////        mediaPlayer.play();
//
//        label.setVisible(true);
//        dialog.setVisible(true);
//    }

    public EndGameDialog(JFrame gameFrame, String message, String source) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(source));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(gameFrame, message, "End Game Dialog", -1);
    }
}
