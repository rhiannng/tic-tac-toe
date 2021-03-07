package persistence;

import model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//TODO citation: code taken from JsonWriterTest.java package in JsonSerializationDemo
public class JsonWriterTest {
    private Board emptyBoard;
    private Board generalBoard;

    @BeforeEach
    void runBefore() {
        emptyBoard = new Board();
        generalBoard = new Board();
        generalBoard.playerMakesAMove("2");
        generalBoard.playerMakesAMove("5");
        generalBoard.otherPlayerMakesAMove("1");
        generalBoard.otherPlayerMakesAMove("3");
        generalBoard.otherPlayerMakesAMove("8");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyBoard() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBoard.json");
            writer.open();
            writer.write(emptyBoard);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBoard.json");
            emptyBoard = reader.read();
            assertEquals(9, emptyBoard.getAllPositions().size());
            assertEquals(9, emptyBoard.getAvailablePositions().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBoard() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBoard.json");
            writer.open();
            writer.write(generalBoard);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBoard.json");
            generalBoard = reader.read();
            assertEquals(9, generalBoard.getAllPositions().size());
            assertEquals(4, generalBoard.getAvailablePositions().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
