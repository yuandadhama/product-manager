package com.controller.createnewfield.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.model.DatabaseModel;
import com.util.Util;

public class WriteData {
    private WriteData() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel) {
        HashMap<String, Object> appDataMap = new HashMap<>();

        // tambahkan data ke hashmap
        appDataMap.put("capital", databaseModel.getCapital());
        appDataMap.put("product", databaseModel.getProductQuantity());
        appDataMap.put("revenue", databaseModel.getRevenue());
        appDataMap.put("profit", databaseModel.getProfit());
        appDataMap.put("totalSellers", databaseModel.getTotalSellers());
        appDataMap.put("sellers", databaseModel.getSellers());

        // convert hashmap menjadi object yang akan ditulis ke file
        JSONObject jsonObject = new JSONObject(appDataMap);

        try (FileWriter fileWriter = new FileWriter(databaseModel.getDbFilePath());) {
            // tulis ke file
            fileWriter.write(jsonObject.toJSONString());

            fileWriter.close();
        } catch (IOException e) {
            Util.println("Cannot write the data");
            e.printStackTrace();
            System.exit(0);
        }

    }
}
