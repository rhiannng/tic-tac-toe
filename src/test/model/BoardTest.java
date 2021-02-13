package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board b;

    @BeforeEach
    void runBefore() {
        b = new Board();
    }

    @Test
    void constructorTest() {
        //checks that symbols are initialized correctly
        assertEquals(" ",b.position1.symbol);
        assertEquals(" ",b.position2.symbol);
        assertEquals(" ",b.position3.symbol);
        assertEquals(" ",b.position4.symbol);
        assertEquals(" ",b.position5.symbol);
        assertEquals(" ",b.position6.symbol);
        assertEquals(" ",b.position7.symbol);
        assertEquals(" ",b.position8.symbol);
        assertEquals(" ",b.position9.symbol);

        //checks that the lists initialized correctly (all elements in them)
        assertEquals(9,b.availablePositions.size());
        assertEquals(9,b.allPositions.size());
        assertTrue(b.allPositions.contains(b.position1));
        assertTrue(b.availablePositions.contains(b.position9));
    }

    @Test
    void playerMakesAMoveTest() {
        b.playerMakesAMove("1");
        b.playerMakesAMove("9");
        b.playerMakesAMove("8");

        //checks availablePosition's size and whether it contains the correct Positions
        assertEquals(6,b.availablePositions.size());
        assertFalse(b.availablePositions.contains(b.position1));
        assertFalse(b.availablePositions.contains(b.position9));
        assertFalse(b.availablePositions.contains(b.position8));
        assertTrue(b.availablePositions.contains(b.position2));
        assertTrue(b.availablePositions.contains(b.position5));

        //checks that the symbols are set correctly
        assertEquals(b.position1.symbol, "X");
        assertEquals(b.position9.symbol, "X");
        assertEquals(b.position8.symbol, "X");
        assertEquals(b.position2.symbol, " ");
        assertEquals(b.position5.symbol, " ");
    }

    @Test
    void stringToPositionTest() {
        assertEquals(b.position1 ,b.stringToPosition("1"));
        assertEquals(b.position2 ,b.stringToPosition("2"));
        assertEquals(b.position9 ,b.stringToPosition("9"));
        assertNotEquals(b.position6 ,b.stringToPosition("7"));
        assertNotEquals(b.position5 ,b.stringToPosition("8"));
    }

    @Test
    void isAValidMoveTest() {
        assertTrue(b.isAValidMove("1"));
        assertTrue(b.isAValidMove("9"));
        assertTrue(b.isAValidMove("8"));
        assertTrue(b.isAValidMove("7"));
        assertFalse(b.isAValidMove(""));
        assertFalse(b.isAValidMove("apple"));
        assertFalse(b.isAValidMove("0"));
    }

    @Test
    void isAnAvailableMoveTest() {
        b.playerMakesAMove("3");
        b.playerMakesAMove("9");

        assertTrue(b.isAnAvailableMove("1"));
        assertTrue(b.isAnAvailableMove("4"));
        assertFalse(b.isAnAvailableMove("3"));
        assertFalse(b.isAnAvailableMove("9"));

        b.playerMakesAMove("1");

        assertFalse(b.isAnAvailableMove("1"));
    }

    @Test
    void moveAgainstPlayerTest() {
        b.playerMakesAMove("2");
        b.moveAgainstPlayer();
        b.moveAgainstPlayer();

        assertEquals(6,b.availablePositions.size());
    }

    //not sure how to test this formally......
    //did run some tests to see what input was given each time so it does do the job!
    @Test
    void getRandomAvailablePositionTest() {
        b.playerMakesAMove("2");
        b.playerMakesAMove("6");

        //checks that method does not choose Positions that have been "taken"
        assertTrue(b.availablePositions.contains(b.getRandomAvailablePosition()));
        assertNotEquals(b.position2,b.getRandomAvailablePosition());
        assertNotEquals(b.position6,b.getRandomAvailablePosition());
    }

    @Test
    void getPositionSymbolTest() {
        b.playerMakesAMove("3");
        b.playerMakesAMove("4");
        b.position1.fillPositionWithO();
        b.position6.fillPositionWithO();

        String p1 = b.getPositionSymbol("1");
        String p2 = b.getPositionSymbol("2");
        String p3 = b.getPositionSymbol("3");
        String p4 = b.getPositionSymbol("4");
        String p5 = b.getPositionSymbol("5");
        String p6 = b.getPositionSymbol("6");

        assertEquals("X",p3);
        assertEquals("X",p4);
        assertEquals("O",p1);
        assertEquals("O",p6);
        assertEquals(" ",p2);
        assertEquals(" ",p5);
    }

    @Test
    void checkEndGameTestTie() {
        b.availablePositions.remove(b.position1);
        b.availablePositions.remove(b.position2);
        b.availablePositions.remove(b.position3);
        b.availablePositions.remove(b.position4);
        b.availablePositions.remove(b.position5);
        b.availablePositions.remove(b.position6);
        b.availablePositions.remove(b.position7);
        b.availablePositions.remove(b.position8);
        b.availablePositions.remove(b.position9);

        assertTrue(b.checkEndGame());
    }

    @Test
    void checkEndGameTestXWins() {
        b.position1.fillPositionWithX();
        b.position5.fillPositionWithX();
        b.position9.fillPositionWithX();

        assertTrue(b.checkEndGame());
    }

    @Test
    void checkEndGameTestOWins() {
        b.position3.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position7.fillPositionWithO();

        assertTrue(b.checkEndGame());
    }

    @Test
    void checkEndGameTestNoEndGame() {
        b.position1.fillPositionWithX();
        b.position3.fillPositionWithX();
        b.position6.fillPositionWithX();
        b.position7.fillPositionWithX();

        b.position2.fillPositionWithO();
        b.position4.fillPositionWithO();
        b.position5.fillPositionWithO();

        assertFalse(b.checkEndGame());
    }

    @Test
    void checkWinTestNoWinsOnBoard() {
        assertFalse(b.checkWin("X"));
        assertFalse(b.checkWin("O"));

        b.position1.fillPositionWithO();
        b.position3.fillPositionWithO();
        b.position9.fillPositionWithO();
        b.position2.fillPositionWithX();

        assertFalse(b.checkWin("X"));
        assertFalse(b.checkWin("O"));
    }

    @Test
    void checkWinTestHorizontalWin() {
        b.position1.fillPositionWithX();
        b.position2.fillPositionWithX();
        b.position3.fillPositionWithX();

        assertTrue(b.checkWin("X"));
        assertFalse(b.checkWin("O"));
    }

    @Test
    void checkWinTestVerticalWin() {
        b.position2.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position8.fillPositionWithO();

        assertTrue(b.checkWin("O"));
        assertFalse(b.checkWin("X"));
    }

    @Test
    void checkWinTestDiagonalWin() {
        b.position1.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position9.fillPositionWithO();

        assertTrue(b.checkWin("O"));
        assertFalse(b.checkWin("X"));
    }

    @Test
    void checkTieTestNoMovesLeftNoWin() {
        b.availablePositions.remove(b.position1);
        b.availablePositions.remove(b.position2);
        b.availablePositions.remove(b.position3);
        b.availablePositions.remove(b.position4);
        b.availablePositions.remove(b.position5);
        b.availablePositions.remove(b.position6);
        b.availablePositions.remove(b.position7);
        b.availablePositions.remove(b.position8);
        b.availablePositions.remove(b.position9);

        b.position1.fillPositionWithX();
        b.position3.fillPositionWithX();
        b.position6.fillPositionWithX();
        b.position7.fillPositionWithX();
        b.position8.fillPositionWithX();

        b.position2.fillPositionWithO();
        b.position4.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position9.fillPositionWithO();

        assertTrue(b.checkTie());
    }

    @Test
    void checkTieTestSomeMovesLeftNoWin() {
        b.availablePositions.remove(b.position1);
        b.availablePositions.remove(b.position2);
        b.availablePositions.remove(b.position3);
        b.availablePositions.remove(b.position4);
        b.availablePositions.remove(b.position5);
        b.availablePositions.remove(b.position6);

        b.position1.fillPositionWithX();
        b.position3.fillPositionWithX();
        b.position5.fillPositionWithX();

        b.position2.fillPositionWithO();
        b.position4.fillPositionWithO();
        b.position6.fillPositionWithO();

        assertFalse(b.checkTie());
    }

    @Test
    void checkTieTestSomeMovesLeftSomeoneWon() {
        b.position1.fillPositionWithX();
        b.position2.fillPositionWithX();
        b.position3.fillPositionWithX();

        b.availablePositions.remove(b.position1);
        b.availablePositions.remove(b.position2);
        b.availablePositions.remove(b.position3);

        assertFalse(b.checkTie());
    }

    @Test
    void checkTieNoMovesLeftSomeoneWon() {
        b.availablePositions.remove(b.position1);
        b.availablePositions.remove(b.position2);
        b.availablePositions.remove(b.position3);
        b.availablePositions.remove(b.position4);
        b.availablePositions.remove(b.position5);
        b.availablePositions.remove(b.position6);
        b.availablePositions.remove(b.position7);
        b.availablePositions.remove(b.position8);
        b.availablePositions.remove(b.position9);

        b.position1.fillPositionWithO();
        b.position4.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position8.fillPositionWithO();
        b.position9.fillPositionWithO();

        b.position2.fillPositionWithX();
        b.position3.fillPositionWithX();
        b.position6.fillPositionWithX();
        b.position7.fillPositionWithX();

        assertFalse(b.checkTie());
    }

    @Test
    void checkHorizontalWinTestFirstRow(){
        b.position1.fillPositionWithX();
        b.position2.fillPositionWithX();
        b.position3.fillPositionWithX();

        assertTrue(b.checkHorizontalWin("X"));
    }

    @Test
    void checkHorizontalWinTestSecondRow(){
        b.position4.fillPositionWithX();
        b.position5.fillPositionWithX();
        b.position6.fillPositionWithX();

        assertTrue(b.checkHorizontalWin("X"));
    }

    @Test
    void checkHorizontalWinTestThirdRow(){
        b.position7.fillPositionWithX();
        b.position8.fillPositionWithX();
        b.position9.fillPositionWithX();

        assertTrue(b.checkHorizontalWin("X"));
    }

    @Test
    void checkHorizontalWinTestNoWin(){
        b.position1.fillPositionWithX();
        b.position2.fillPositionWithX();
        b.position4.fillPositionWithX();
        b.position7.fillPositionWithX();

        assertFalse(b.checkHorizontalWin("X"));
    }

    @Test
    void checkVerticalWinTestFirstColumn(){
        b.position1.fillPositionWithX();
        b.position4.fillPositionWithX();
        b.position7.fillPositionWithX();

        assertTrue(b.checkVerticalWin("X"));
    }

    @Test
    void checkVerticalWinTestSecondColumn(){
        b.position2.fillPositionWithX();
        b.position5.fillPositionWithX();
        b.position8.fillPositionWithX();

        assertTrue(b.checkVerticalWin("X"));
    }

    @Test
    void checkVerticalWinTestThirdColumn(){
        b.position3.fillPositionWithX();
        b.position6.fillPositionWithX();
        b.position9.fillPositionWithX();

        assertTrue(b.checkVerticalWin("X"));
    }

    @Test
    void checkVerticalWinTestNoWin(){
        b.position1.fillPositionWithX();
        b.position5.fillPositionWithX();
        b.position9.fillPositionWithX();
        b.position4.fillPositionWithX();

        assertFalse(b.checkVerticalWin("X"));
    }

    @Test
    void checkDiagonalWinTestDiagonalWins() {
        b.position3.fillPositionWithX();
        b.position5.fillPositionWithX();
        b.position7.fillPositionWithX();

        assertTrue(b.checkDiagonalWin("X"));

        b.position1.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position9.fillPositionWithO();

        assertTrue(b.checkDiagonalWin("O"));
    }

    @Test
    void checkDiagonalWinTestNoDiagonal() {
        b.position1.fillPositionWithX();
        b.position2.fillPositionWithX();
        b.position3.fillPositionWithX();

        assertFalse(b.checkDiagonalWin("X"));

        b.position1.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position7.fillPositionWithO();

        assertFalse(b.checkDiagonalWin("O"));
    }

    @Test
    void checkSameSymbolTest() {
        String s1 = "X";
        String s2 = "O";
        String s3 = "apple";

        assertTrue(b.checkSameSymbol(s1,s1,s1));
        assertFalse(b.checkSameSymbol(s1,s1,s2));
        assertFalse(b.checkSameSymbol(s1,s2,s3));
    }
}