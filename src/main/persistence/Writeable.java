package persistence;

import org.json.JSONObject;

//TODO citation: code taken from Writeable.java package in JsonSerializationDemo
public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
