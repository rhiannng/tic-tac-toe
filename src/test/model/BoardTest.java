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
    void constructorTest(){
        assertEquals(b.availablePositions.size(),9);
        assertEquals("",b.position1.symbol);
        assertEquals("",b.position2.symbol);
        assertEquals("",b.position3.symbol);
        assertEquals("",b.position4.symbol);
        assertEquals("",b.position5.symbol);
        assertEquals("",b.position6.symbol);
        assertEquals("",b.position7.symbol);
        assertEquals("",b.position8.symbol);
        assertEquals("",b.position9.symbol);
    }

    @Test
    void playerMakesAMoveTest(){
        b.playerMakesAMove("1");
        b.playerMakesAMove("9");
        b.playerMakesAMove("8");

        assertEquals(6,b.availablePositions.size());
        assertFalse(b.availablePositions.contains(b.position1));
        assertFalse(b.availablePositions.contains(b.position9));
        assertFalse(b.availablePositions.contains(b.position8));
        assertTrue(b.availablePositions.contains(b.position2));
        assertTrue(b.availablePositions.contains(b.position5));
    }

    @Test
    void moveAgainstPlayerTest(){
        b.playerMakesAMove("2");
        b.moveAgainstPlayer();
        b.moveAgainstPlayer();

        assertEquals(6,b.availablePositions.size());
        assertFalse(b.availablePositions.contains(b.position2));
    }

    //not sure how to test this formally......
    //did run some tests to see what input was given each time so it does do the job!
    @Test
    void getRandomAvailablePositionTest(){
        b.playerMakesAMove("2");
        b.playerMakesAMove("6");

        assertTrue(b.availablePositions.contains(b.getRandomAvailablePosition()));
        assertNotEquals(b.position2,b.getRandomAvailablePosition());
        assertNotEquals(b.position6,b.getRandomAvailablePosition());
    }
}
