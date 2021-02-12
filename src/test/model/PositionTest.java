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
        p1 = new Position("","1");
        p2 = new Position("X","2");
        p3 = new Position("O","3");
    }

    @Test
    void fillPositionXTest() {
        p1.fillPositionWithX();
        p2.fillPositionWithX();
        p3.fillPositionWithX();

        assertEquals("X",p1.symbol);
        assertEquals("X",p2.symbol);
        assertEquals("X",p3.symbol);
    }

    @Test
    void fillPositionOTest() {
        p1.fillPositionWithO();
        p2.fillPositionWithO();
        p3.fillPositionWithO();

        assertEquals("O",p1.symbol);
        assertEquals("O",p2.symbol);
        assertEquals("O",p3.symbol);
    }
}