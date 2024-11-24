package com.controller.common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.model.DatabaseModel;
import com.util.Util;

public class ConvertModelToField {
    private ConvertModelToField() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converts the provided {@link DatabaseModel} instance into a JSON object
     * and writes it to the specified file path in the model.
     * <p>
     * The following properties are extracted from the model and written to the JSON
     * file:
     * <ul>
     * <li>Capital</li>
     * <li>Product quantity</li>
     * <li>Revenue</li>
     * <li>Profit</li>
     * <li>Total sellers</li>
     * <li>Sellers</li>
     * <li>Unique key</li>
     * </ul>
     * </p>
     *
     * @param databaseModel The {@link DatabaseModel} instance containing data to be
     *                      converted
     *                      and saved.
     * @throws IllegalArgumentException if the {@code databaseModel} is null.
     */
    public static void function(DatabaseModel databaseModel) {
        HashMap<String, Object> appDataMap = new HashMap<>();

        // tambahkan data ke hashmap
        appDataMap.put("capital", databaseModel.getCapital());
        appDataMap.put("product", databaseModel.getProductQuantity());
        appDataMap.put("revenue", databaseModel.getRevenue());
        appDataMap.put("profit", databaseModel.getProfit());
        appDataMap.put("totalSellers", databaseModel.getTotalSellers());
        appDataMap.put("sellers", databaseModel.getSellers());
        appDataMap.put("uniqueKey", databaseModel.getUniqueKey());

        // convert hashmap menjadi object yang akan ditulis ke file
        JSONObject db = new JSONObject(appDataMap);
        try {
            Util.writeJsonToFile(db, databaseModel.getDbFilePath());
        } catch (IOException e) {
            Util.println("Cannot write the data");
            e.printStackTrace();
            System.exit(0);
        }

    }
}
