package com.controller.addcustomer.utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class SelectSeller {
    private SelectSeller() {
        throw new IllegalStateException("Utility class");
    }

    public static JSONObject function(DatabaseModel databaseModel, AppView view) {
        String dbFilePath = databaseModel.getDbFilePath();
        try (FileReader fileReader = new FileReader(dbFilePath)) {
            JSONParser jsonParser = new JSONParser();
            JSONObject db = (JSONObject) jsonParser.parse(fileReader);
            JSONObject sellersDb = (JSONObject) db.get("sellers");

            // tampilkan nama nama seller
            @SuppressWarnings("unchecked")
            List<String> sellerNames = new ArrayList<>(sellersDb.keySet());
            
            view.sellersListView(sellerNames);
            

            return selectSeller(sellersDb, sellerNames);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
    private static JSONObject selectSeller(JSONObject sellersDb, List<String> sellerNames) {
        boolean showWarning = false;
        while (true) {
            if (showWarning) {
                Util.println("Please choose the appropriate seller: 1 " + (sellerNames.size() > 1 ? "- " + sellerNames.size() : ""));
            }
            int sellerIndex = Util.getUserInt("Choose seller: ");

            if (sellerIndex < 1 || sellerIndex > sellerNames.size()) {
               showWarning = true; 
            } else {
                String sellerName = sellerNames.get(sellerIndex - 1);
                return (JSONObject) sellersDb.get(sellerName);
            }
        }
    }
}
