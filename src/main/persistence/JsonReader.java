package persistence;

import model.Board;
import model.Position;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//TODO citation: code taken and modified from JsonReader.java package in JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS : reads the board from file and returns it, throws IOException if an error occurs when reading file
    public Board read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBoard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    //MODIFIES: Board b and its fields
    //EFFECTS: parses board from JSONObject, setting up fields, and then returns it
    private Board parseBoard(JSONObject jsonObject) {
        Board b = new Board();
        setUpPositions1To9(b,jsonObject);
        setUpAllPositions(b);
        setUpAvailablePositions(b,jsonObject);
        return b;
    }

    //MODIFIES: Board b
    //EFFECTS : sets b's position fields to the already parsed positions from jsonObject
    private void setUpPositions1To9(Board b, JSONObject jsonObject) {
        b.setPosition1(parsePosition((JSONObject) jsonObject.get("position1")));
        b.setPosition2(parsePosition((JSONObject) jsonObject.get("position2")));
        b.setPosition3(parsePosition((JSONObject) jsonObject.get("position3")));
        b.setPosition4(parsePosition((JSONObject) jsonObject.get("position4")));
        b.setPosition5(parsePosition((JSONObject) jsonObject.get("position5")));
        b.setPosition6(parsePosition((JSONObject) jsonObject.get("position6")));
        b.setPosition7(parsePosition((JSONObject) jsonObject.get("position7")));
        b.setPosition8(parsePosition((JSONObject) jsonObject.get("position8")));
        b.setPosition9(parsePosition((JSONObject) jsonObject.get("position9")));
    }

    //EFFECTS: parses a Position from jsonObject and returns it
    private Position parsePosition(JSONObject jsonObject) {
        String symbol = jsonObject.getString("symbol");
        String positionNumber = jsonObject.getString("positionNumber");
        Position p = new Position(symbol, positionNumber);
        return p;
    }

    //REQUIRES: positions have already been parsed from jsonObject before being called
    //MODIFIES: Board b
    //EFFECTS : updates the allPositions field in Board b
    private void setUpAllPositions(Board b) {
        b.resetAllPositions();
    }

    //MODIFIES: Board b
    //EFFECTS : parses available objects from jsonObject, then sets b's availablePosition field
    private void setUpAvailablePositions(Board b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("availablePositions");
        ArrayList<Position> availPosList = new ArrayList<>();
        for (Object json : jsonArray) {
            Position p = parsePosition((JSONObject) json);
            availPosList.add(p);
        }
        b.loadJsonAvailablePositions(availPosList);
    }
}
