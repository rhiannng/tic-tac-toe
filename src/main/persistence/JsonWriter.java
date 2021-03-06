package persistence;

import model.Board;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//represents a writer that writes JSON representation of a board to file
//TODO citation: code taken and modified from JsonWriter.java package in JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS : opens writer; throws FileNotFound exception if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS : writes JSON representation of the board to file
    public void write(Board b) {
        JSONObject json = b.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS : writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

    //MODIFIES: this
    //EFFECTS : closes writer
    public void close() {
        writer.close();
    }
}
