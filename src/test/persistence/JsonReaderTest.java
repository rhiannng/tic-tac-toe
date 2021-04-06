package persistence;

import model.Board;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//TODO citation: code taken and modified from JsonReaderTest.java package in JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Board b = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyBoard() {
        JsonReader reader = new JsonReader("./data/testEmptyBoard.json");
        try {
            Board b = reader.read();
            assertEquals(9, b.getAllPositions().size());
            assertEquals(9, b.getAvailablePositions().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBoard1() {
        JsonReader reader = new JsonReader("./data/testGeneralBoard1.json");
        try {
            Board b = reader.read();
            assertEquals(9, b.getAllPositions().size());
            assertEquals(4, b.getAvailablePositions().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBoard2() {
        JsonReader reader = new JsonReader("./data/testGeneralBoard2.json");
        try {
            Board b = reader.read();
            assertEquals(9, b.getAllPositions().size());
            assertEquals(6, b.getAvailablePositions().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
