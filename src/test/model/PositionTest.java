package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Position p1;
    Position p2;
    Position p3;

    @BeforeEach
    void runBefore() {
        p1 = new Position("");
        p2 = new Position("X");
        p3 = new Position("O");
    }

    @Test
    void fillPositionXTest() {
        p1.fillPositionX();
        p2.fillPositionX();
        p3.fillPositionX();

        assertEquals("X",p1.symbol);
        assertEquals("X",p2.symbol);
        assertEquals("X",p3.symbol);
    }

    @Test
    void fillPositionOTest() {
        p1.fillPositionO();
        p2.fillPositionO();
        p3.fillPositionO();

        assertEquals("O",p1.symbol);
        assertEquals("O",p2.symbol);
        assertEquals("O",p3.symbol);
    }

    @Test
    void emptyPositionTest() {
        p1.emptyPosition();
        p2.emptyPosition();
        p3.emptyPosition();

        assertEquals("",p1.symbol);
        assertEquals("",p2.symbol);
        assertEquals("",p3.symbol);
    }
}