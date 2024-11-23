package com.controller.showexistingfield.utility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.model.DatabaseModel;

public class GetDataFromDb {
    private GetDataFromDb() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, String dbFile) {
        // baca file json yang dipilih
        try (FileReader fileReader = new FileReader(databaseModel.getDbDirectoryPath() + dbFile)) {  
            setData(databaseModel, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setData(DatabaseModel databaseModel, FileReader reader) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject db = (JSONObject) jsonParser.parse
            (reader);   
            // ambil data dari file database untuk ditampilkan
            // convert and set data
            databaseModel.setCapital(((Number) db.get("capital")).intValue());
            databaseModel.setProductQuantity(((Number) db.get("product")).intValue());
            databaseModel.setRevenue(((Number) db.get("revenue")).intValue());
            databaseModel.setProfit(((Number) db.get("profit")).intValue());

            // ambil data untuk seller json object
            databaseModel.setSellers((JSONObject) db.get("sellers"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }    
    }
}
