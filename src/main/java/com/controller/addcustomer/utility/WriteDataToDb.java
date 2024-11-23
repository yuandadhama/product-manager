package com.controller.addcustomer.utility;

import org.json.simple.JSONObject;

import com.model.DatabaseModel;
import com.util.Util;

public class WriteDataToDb {
    private WriteDataToDb() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("unchecked")
    public static void function(DatabaseModel databaseModel) {
        try {
            JSONObject db = Util.readFileToJson(databaseModel.getDbFilePath());
            db.put("product", databaseModel.getProductQuantity());
            db.put("revenue", databaseModel.getRevenue());
            
            Util.writeJsonToFile(db, databaseModel.getDbFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
