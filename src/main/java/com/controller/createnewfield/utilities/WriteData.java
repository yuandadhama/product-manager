package com.controller.createnewfield.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.model.DatabaseModel;

public class WriteData {
    private WriteData() {
        throw new IllegalStateException("Utility class");
    }
    public static void function(DatabaseModel databaseModel) throws IOException {
        HashMap<String, Object> appDataMap = new HashMap<>();

        // tambahkan data ke hashmap
        appDataMap.put("capital", databaseModel.getCapital());
        appDataMap.put("product", databaseModel.getProduct());
        appDataMap.put("revenue", databaseModel.getRevenue());
        appDataMap.put("profit", databaseModel.getProfit());
        appDataMap.put("totalSellers", databaseModel.getTotalSellers());
        appDataMap.put("sellers", databaseModel.getSellers());

        // convert hashmap menjadi object yang akan ditulis ke file
        JSONObject jsonObject = new JSONObject(appDataMap);

        // tulis ke file
        FileWriter fileWriter = new FileWriter(databaseModel.getDbFilePath());

        fileWriter.write(jsonObject.toJSONString());

        fileWriter.close();
    }
}
