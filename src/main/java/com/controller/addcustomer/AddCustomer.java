package com.controller.addcustomer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.controller.addcustomer.utility.SelectPackage;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class AddCustomer {
    public static void function(DatabaseModel databaseModel, AppView view) {
        int selectedPackage = SelectPackage.function(view);
        
        String dbFilePath = databaseModel.getDbFilePath();
        JSONObject db = null;
        JSONObject selectedSeller = null;
        JSONObject sellersDb = null;
        String sellerName = null;

        try (FileReader fileReader = new FileReader(dbFilePath)) {
            JSONParser jsonParser = new JSONParser();
            try {
                db = (JSONObject) jsonParser.parse(fileReader);
                sellersDb = (JSONObject) db.get("sellers");

                List<String> sellerKeys = new ArrayList<>(sellersDb.keySet());

                int order = 1;
                for (String seller : sellerKeys) {
                    System.out.println("("+order+") " + seller);
                    order++;
                }

                System.out.print("Choose seller: ");
                int sellerIndex = Util.getUserInt("Choose seller:");

                sellerName = sellerKeys.get(sellerIndex);

                selectedSeller = (JSONObject) sellersDb.get(sellerName);

                System.out.println(selectedSeller);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
