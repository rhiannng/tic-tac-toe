package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.util.ArrayList;
import java.util.HashMap;

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
        assertEquals(b.position1, b.allPositions.get("1"));
        assertEquals(b.position9, b.availablePositions.get("9"));
    }

    @Test
    void playerMakesAMoveStringParameterTest() {
        b.playerMakesAMove("1");
        b.playerMakesAMove("9");
        b.playerMakesAMove("8");

        //checks availablePosition's size and whether it contains the correct Positions
        assertEquals(6,b.availablePositions.size());
        assertNull(b.availablePositions.get("1"));
        assertNull(b.availablePositions.get("9"));
        assertNull(b.availablePositions.get("8"));
        assertEquals(b.position2, b.availablePositions.get("2"));
        assertEquals(b.position5, b.availablePositions.get("5"));

        //checks that the symbols are set correctly
        assertEquals(b.position1.symbol, "X");
        assertEquals(b.position9.symbol, "X");
        assertEquals(b.position8.symbol, "X");
        assertEquals(b.position2.symbol, " ");
        assertEquals(b.position5.symbol, " ");
    }

    @Test
    void playerMakesAMovePositionParameterTest() {
        b.playerMakesAMove(b.position1);
        b.playerMakesAMove(b.position9);
        b.playerMakesAMove(b.position8);

        //checks availablePosition's size and whether it contains the correct Positions
        assertEquals(6,b.availablePositions.size());
        assertNull(b.availablePositions.get("1"));
        assertNull(b.availablePositions.get("9"));
        assertNull(b.availablePositions.get("8"));
        assertEquals(b.position2, b.availablePositions.get("2"));
        assertEquals(b.position5, b.availablePositions.get("5"));

        //checks that the symbols are set correctly
        assertEquals(b.position1.symbol, "X");
        assertEquals(b.position9.symbol, "X");
        assertEquals(b.position8.symbol, "X");
        assertEquals(b.position2.symbol, " ");
        assertEquals(b.position5.symbol, " ");
    }

    @Test
    void otherPlayerMakesAMoveTest() {
        b.otherPlayerMakesAMove("2");
        b.otherPlayerMakesAMove("5");
        b.otherPlayerMakesAMove("8");

        //checks availablePosition's size and whether it contains the correct Positions
        assertEquals(6,b.availablePositions.size());
        assertEquals(b.position1, b.availablePositions.get("1"));
        assertEquals(b.position9, b.availablePositions.get("9"));
        assertNull(b.availablePositions.get("8"));
        assertNull(b.availablePositions.get("2"));
        assertNull(b.availablePositions.get("5"));

        //checks that the symbols are set correctly
        assertEquals(b.position1.symbol, " ");
        assertEquals(b.position9.symbol, " ");
        assertEquals(b.position8.symbol, "O");
        assertEquals(b.position2.symbol, "O");
        assertEquals(b.position5.symbol, "O");
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
        Position randomAvailablePosition = b.getRandomAvailablePosition();
        String posKey = randomAvailablePosition.getPositionNumber();
        assertEquals(randomAvailablePosition, b.availablePositions.get(posKey));
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
    void checkEndGameTestTie() {
        b.availablePositions.remove("1");
        b.availablePositions.remove("2");
        b.availablePositions.remove("3");
        b.availablePositions.remove("4");
        b.availablePositions.remove("5");
        b.availablePositions.remove("6");
        b.availablePositions.remove("7");
        b.availablePositions.remove("8");
        b.availablePositions.remove("9");

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
        b.availablePositions.remove("1");
        b.availablePositions.remove("2");
        b.availablePositions.remove("3");
        b.availablePositions.remove("4");
        b.availablePositions.remove("5");
        b.availablePositions.remove("6");
        b.availablePositions.remove("7");
        b.availablePositions.remove("8");
        b.availablePositions.remove("9");

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
        b.availablePositions.remove("1");
        b.availablePositions.remove("2");
        b.availablePositions.remove("3");
        b.availablePositions.remove("4");
        b.availablePositions.remove("5");
        b.availablePositions.remove("6");

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

        b.availablePositions.remove("1");
        b.availablePositions.remove("2");
        b.availablePositions.remove("3");

        assertFalse(b.checkTie());
    }

    @Test
    void checkTieNoMovesLeftSomeoneWon() {
        b.availablePositions.remove("1");
        b.availablePositions.remove("2");
        b.availablePositions.remove("3");
        b.availablePositions.remove("4");
        b.availablePositions.remove("5");
        b.availablePositions.remove("6");
        b.availablePositions.remove("7");
        b.availablePositions.remove("8");
        b.availablePositions.remove("9");

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
    void checkTieTestNoMovesLeftBothWin() {
        b.availablePositions.remove("1");
        b.availablePositions.remove("2");
        b.availablePositions.remove("3");
        b.availablePositions.remove("4");
        b.availablePositions.remove("5");
        b.availablePositions.remove("6");
        b.availablePositions.remove("7");
        b.availablePositions.remove("8");
        b.availablePositions.remove("9");

        b.position1.fillPositionWithX();
        b.position2.fillPositionWithX();
        b.position3.fillPositionWithX();
        b.position7.fillPositionWithX();
        b.position9.fillPositionWithX();

        b.position4.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position6.fillPositionWithO();
        b.position8.fillPositionWithO();

        assertFalse(b.checkTie());
    }

    @Test
    void checkTieTestSomeMovesLeftBothWin() {
        b.availablePositions.remove("1");
        b.availablePositions.remove("2");
        b.availablePositions.remove("3");
        b.availablePositions.remove("4");
        b.availablePositions.remove("5");
        b.availablePositions.remove("6");

        b.position1.fillPositionWithX();
        b.position2.fillPositionWithX();
        b.position3.fillPositionWithX();

        b.position4.fillPositionWithO();
        b.position5.fillPositionWithO();
        b.position6.fillPositionWithO();

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

    @Test
    void resetAllPositionsTest() {
        b.allPositions.remove("1");
        b.allPositions.remove("2");
        b.allPositions.remove("9");

        b.resetAllPositions();

        assertEquals(9,b.allPositions.size());
    }

    @Test
    void loadJsonAvailablePositionsTest() {
        ArrayList<Position> posList = new ArrayList<>();
        posList.add(new Position(" ","1"));
        posList.add(new Position(" ","2"));
        posList.add(new Position(" ","3"));
        posList.add(new Position(" ","4"));

        b.loadJsonAvailablePositions(posList);
        assertEquals(4, b.availablePositions.size());
        assertEquals(b.position1, b.availablePositions.get("1"));
        assertEquals(b.position2, b.availablePositions.get("2"));
        assertEquals(b.position3, b.availablePositions.get("3"));
        assertEquals(b.position4, b.availablePositions.get("4"));
        assertNull(b.availablePositions.get("5"));
        assertNull(b.availablePositions.get("6"));
    }

    @Test
    void checkIfMatchTest() {
        b.availablePositions.remove("1");
        b.availablePositions.remove("5");
        b.availablePositions.remove("7");

        ArrayList<Position> list = new ArrayList<>(b.availablePositions.values());

        assertFalse(b.checkIfMatch(b.position1, list));
        assertFalse(b.checkIfMatch(b.position5, list));
        assertFalse(b.checkIfMatch(b.position7, list));
        assertTrue(b.checkIfMatch(b.position2, list));
        assertTrue(b.checkIfMatch(b.position3, list));
    }

    @Test
    void toJsonTest() {
        b.position4.fillPositionWithX();
        b.position6.fillPositionWithX();
        b.position7.fillPositionWithO();

        b.availablePositions.remove("4");
        b.availablePositions.remove("6");
        b.availablePositions.remove("7");

        JSONObject json = b.toJson();
        JsonReader reader = new JsonReader("");
        b = reader.parseBoard(json);

        assertEquals(6,b.availablePositions.size());
        assertEquals(b.position1, b.availablePositions.get("1"));
        assertEquals(b.position2, b.availablePositions.get("2"));
        assertEquals(b.position3, b.availablePositions.get("3"));
        assertNull(b.availablePositions.get("4"));
        assertNull(b.availablePositions.get("6"));
        assertNull(b.availablePositions.get("7"));
        assertEquals("X",b.position4.symbol);
        assertEquals("X",b.position6.symbol);
        assertEquals("O",b.position7.symbol);

    }
}