package com.util.utilsfunction;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadFileToJson {
    private ReadFileToJson() {
        throw new IllegalStateException("Utility class");
    }

    public static JSONObject function(String filePath) throws IOException, ParseException {

        // Parse JSON from file into a JSONObject
        FileReader reader = new FileReader(filePath);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(reader);
    }

}
