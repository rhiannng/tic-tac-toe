package ui;

import model.Board;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// represents the tic-tac-toe game application
//TODO citation: code taken and modified from WorkRoomApp.java package from JsonSerializationDemo
//TODO citation: centreOnScreen method taken from SpaceInvaders package from B02-SpaceInvadersBase
public class Game extends JFrame {
    private static final String FILE_DIRECTORY = "./data/myTicTacToeGame.json";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private Board board;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    BoardPanel boardPanel;
    JPanel optionsPanel;
    GridBagConstraints constraints;

    //EFFECTS: runs the game application
    public Game() {
        super("Tic Tac Toe Game");
        setLayout(new GridBagLayout());
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        board = new Board();
        input = new Scanner(System.in);
        constraints = new GridBagConstraints();
        jsonWriter = new JsonWriter(FILE_DIRECTORY);
        jsonReader = new JsonReader(FILE_DIRECTORY);
        initializeBoardPanel(board);
        initializeOptionsPanel();
        centreOnScreen();
        setVisible(true);
//        runConsoleGame();
    }

    //EFFECTS: displays instructions and processes user input
    private void runConsoleGame() {
        boolean keepGoing = true;
        displayInstructions();
        displayBoardWithNumbers();

        while (keepGoing) {
            System.out.println("Enter a position number to place an X");
            String command = input.nextLine();
            keepGoing = processCommand(command);
        }
    }

    //MODIFIES: boolean keepGoing
    //EFFECTS : processes user command and calls the appropriate methods, returns true if game continues
    private boolean processCommand(String command) {
        boolean keepGoing = true;
        if (board.isAnAvailableMove(command) && board.isAValidMove(command)) {
            keepGoing = playingGame(command);
        } else if (command.equals("q")) {
            System.out.println("Thanks for playing!");
            keepGoing = false;
        } else if (command.equals("r")) {
            System.out.println("Restarting!");
            keepGoing = false;
            new Game();
        } else if (command.equals("s")) {
            saveGame();
        } else if (command.equals("l")) {
            loadGame();
        } else {
            System.out.println("Please reenter the position number, that was either not valid or available.");
        }
        return keepGoing;
    }


    //MODIFIES: Board b, boolean keepGoing
    //EFFECTS : given the board and command, continues the game and checks for end game states. returns true if no
    //          end game states have been triggered
    private boolean playingGame(String command) {
        boolean keepGoing = true;
        board.playerMakesAMove(command);
        displayBoard();
        if (board.checkEndGame()) {
            keepGoing = false;
            endGameSequence();
        } else {
            System.out.println("Here's my move!");
            board.moveAgainstPlayer();
            displayBoard();
            if (board.checkEndGame()) {
                keepGoing = false;
                endGameSequence();
            }
        }
        return keepGoing;
    }

    //EFFECTS: prints initial instruction statements
    private void displayInstructions() {
        System.out.println("Let's play Tic-tac-toe!");
        System.out.println("You will play as X. Enter a number to place the X on the corresponding position.");
        System.out.println("If you would like quit, just type \"q\".");
        System.out.println("If you would like to restart the game, just type \"r\".");
        System.out.println("To save the game, just type \"s\".");
        System.out.println("To load a saved game, just type \"l\".");
        System.out.println("You can go first! Here is the board with the position numbers!");
    }

    //EFFECTS: prints the board with position numbers on it
    private void displayBoardWithNumbers() {
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9 ");
    }

    //EFFECTS: prints the current game board
    private void displayBoard() {
        String p1 = board.getPositionSymbol("1");
        String p2 = board.getPositionSymbol("2");
        String p3 = board.getPositionSymbol("3");
        String p4 = board.getPositionSymbol("4");
        String p5 = board.getPositionSymbol("5");
        String p6 = board.getPositionSymbol("6");
        String p7 = board.getPositionSymbol("7");
        String p8 = board.getPositionSymbol("8");
        String p9 = board.getPositionSymbol("9");

        System.out.println(" " + p1 + " | " + p2 + " | " + p3 + " ");
        System.out.println("---+---+---");
        System.out.println(" " + p4 + " | " + p5 + " | " + p6 + " ");
        System.out.println("---+---+---");
        System.out.println(" " + p7 + " | " + p8 + " | " + p9 + " ");
    }

    //EFFECTS: prints statements win/lose/tie statements
    private void endGameSequence() {

        if (board.checkWin("X")) {
            System.out.println("You won!");
        } else if (board.checkWin("O")) {
            System.out.println("You lost!");
        } else if (board.checkTie()) {
            System.out.println("We tied!");
        }

        System.out.println("Would you like to play again?");
        System.out.println("Please input \"yes\" to play again, if not, press any button.");
        if (input.nextLine().equals("yes")) {
            new Game();
        }
    }

    //EFFECTS : saves the current Board to a json file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(board);
            jsonWriter.close();
            System.out.println("Saved the board to" + FILE_DIRECTORY);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file to:" + FILE_DIRECTORY);
        }
    }

    //EFFECTS : loads Board from file
    private void loadGame() {
        try {
            board = jsonReader.read();
            System.out.println("Loaded the board from" + FILE_DIRECTORY);
            displayBoard();
            boardPanel.setVisible(false);
            initializeBoardPanel(board);
        } catch (IOException e) {
            System.out.println("Unable to find file:" + FILE_DIRECTORY);
        }
    }

    private void initializeBoardPanel(Board b) {
        boardPanel = new BoardPanel(b);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(boardPanel, constraints);
        setVisible(true);
    }

    private void initializeOptionsPanel() {
        optionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        initializeSaveButton(c);
        c.gridy++;
        initializeLoadButton(c);
        c.gridy++;
        initializeRestartButton(c);
        c.gridy++;
        initializeQuitButton(c);

        constraints.gridx = 1;
        constraints.gridy = 0;

        add(optionsPanel, constraints);
    }

    private void initializeSaveButton(GridBagConstraints c) {
        JButton saveButton = new JButton("Save");
        optionsPanel.add(saveButton,c);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });
    }

    private void initializeLoadButton(GridBagConstraints c) {
        JButton loadButton = new JButton("Load");
        optionsPanel.add(loadButton,c);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
    }

    private void initializeRestartButton(GridBagConstraints c) {
        JButton restartButton = new JButton("Restart");
        optionsPanel.add(restartButton,c);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }

    private void initializeQuitButton(GridBagConstraints c) {
        JButton quitButton = new JButton("Quit");
        optionsPanel.add(quitButton,c);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    private void restartGame() {
        board = new Board();
        boardPanel.setVisible(false);
        initializeBoardPanel(board);
    }
}
