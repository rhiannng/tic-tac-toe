package ui;

import model.Board;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;

// represents the tic-tac-toe game application
public class Game {
    private static final String FILE_DIRECTORY = "./data/ticTacToeGame.json";
    private Board board;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the game application
    public Game() {
        board = new Board();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(FILE_DIRECTORY);
        jsonReader = new JsonReader(FILE_DIRECTORY);
        runGame();
    }

    //EFFECTS: processes user input and prints statement for the user to read
    public void runGame() {
        boolean keepGoing = true;
        displayInstructions();
        displayBoardWithNumbers();

        while (keepGoing) {
            System.out.println("Enter a position number to place an X");
            String command = input.nextLine();
            keepGoing = processCommand(command);
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS :
    public boolean processCommand(String command) {
        boolean keepGoing = true;
        if (board.isAnAvailableMove(command) && board.isAValidMove(command)) {
            keepGoing = playingGame(command);
        } else if (command.equals("q")) {
            System.out.println("Thanks for playing!");
            keepGoing = false;
        } else if (command.equals("r")) {
            System.out.println("Restarting!");
            //TODO: MAKE A RESTART METHOD OR SOME SHIT IDK
//        } else if (command.equals("s")) {
//            //TODO: SAVE GAME METHOD
//        } else if (command.equals("l")) {
//            //TODO: LOAD GAME METHOD
        } else {
            System.out.println("Please reenter the position number, that was either not valid or available.");
        }
        return keepGoing;
    }


    //MODIFIES: Board b, boolean keepGoing
    //EFFECTS : given the board and command, continues the game and checks for end game states. returns true if no
    //          end game states have been triggered
    public boolean playingGame(String command) {
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
    public void displayInstructions() {
        System.out.println("Let's play Tic-tac-toe!");
        System.out.println("You will play as X. Enter a number to place the X on the corresponding position.");
        System.out.println("If you would like quit, just type \"q\".");
        System.out.println("If you would like to restart the game, just type \"r\".");
        System.out.println("You can go first! Here is the board with the position numbers!");
    }

    //EFFECTS: prints the board with position numbers on it
    public void displayBoardWithNumbers() {
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9 ");
    }

    //EFFECTS: prints the current game board
    public void displayBoard() {
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
    public void endGameSequence() {

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
            runGame();
            //TODO: FIGURE OUT PROPER RESTART METHOD
        }
    }
}
