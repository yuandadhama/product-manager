package com.util.utilsfunction;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class WriteJsonToFile {
    private WriteJsonToFile() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(JSONObject jsonObject, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonObject.toJSONString());
        }
    }

}
