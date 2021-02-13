package ui;

import model.Board;
import java.util.Scanner;

// represents the tic-tac-toe game application
public class Game {

    //EFFECTS: runs the game application
    public Game() {
        runGame();
    }

    //EFFECTS: processes user input and prints statement for the user to read
    public void runGame() {
        Board b = new Board();
        boolean keepGoing = true;
        Scanner input = new Scanner(System.in);
        displayInstructions();
        displayBoardWithNumbers();

        while (keepGoing) {
            System.out.println("Enter a position number to place an X");
            String command = input.nextLine();

            if (b.isAnAvailableMove(command) && b.isAValidMove(command)) {
                keepGoing = playingGame(b,command);

            } else if (command.equals("q")) {
                System.out.println("Thanks for playing!");
                keepGoing = false;

            } else if (command.equals("r")) {
                System.out.println("Restarting!");
                keepGoing = false;
                runGame();

            } else {
                System.out.println("Please reenter the position number, that was either not valid or available.");
            }
        }
    }

    //MODIFIES: Board b, boolean keepGoing
    //EFFECTS : given the board and command, continues the game and checks for end game states. returns true if no
    //          end game states have been triggered
    public boolean playingGame(Board b, String command) {
        boolean keepGoing = true;
        b.playerMakesAMove(command);
        displayBoard(b);
        if (b.checkEndGame()) {
            keepGoing = false;
            endGameSequence(b);
        } else {
            System.out.println("Here's my move!");
            b.moveAgainstPlayer();
            displayBoard(b);
            if (b.checkEndGame()) {
                keepGoing = false;
                endGameSequence(b);
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
    public void displayBoard(Board b) {
        String p1 = b.getPositionSymbol("1");
        String p2 = b.getPositionSymbol("2");
        String p3 = b.getPositionSymbol("3");
        String p4 = b.getPositionSymbol("4");
        String p5 = b.getPositionSymbol("5");
        String p6 = b.getPositionSymbol("6");
        String p7 = b.getPositionSymbol("7");
        String p8 = b.getPositionSymbol("8");
        String p9 = b.getPositionSymbol("9");

        System.out.println(" " + p1 + " | " + p2 + " | " + p3 + " ");
        System.out.println("---+---+---");
        System.out.println(" " + p4 + " | " + p5 + " | " + p6 + " ");
        System.out.println("---+---+---");
        System.out.println(" " + p7 + " | " + p8 + " | " + p9 + " ");
    }

    //EFFECTS: prints statements win/lose/tie statements
    public void endGameSequence(Board b) {
        Scanner input = new Scanner(System.in);

        if (b.checkWin("X")) {
            System.out.println("You won!");
        } else if (b.checkWin("O")) {
            System.out.println("You lost!");
        } else if (b.checkTie()) {
            System.out.println("We tied!");
        }

        System.out.println("Would you like to play again?");
        System.out.println("Please input \"yes\" to play again, if not, press any button.");
        if (input.nextLine().equals("yes")) {
            runGame();
        }
    }
}
