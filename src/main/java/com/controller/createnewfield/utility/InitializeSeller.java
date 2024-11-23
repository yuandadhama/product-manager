package com.controller.createnewfield.utility;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.model.DatabaseModel;
import com.util.Util;
import com.model.SellerModel;

public class InitializeSeller {
    private InitializeSeller() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel database) {
        HashMap<Object, JSONObject> sellersMap = new HashMap<>();

        Util.println("== Sellers Data ==");
        for (int i = 1; i <= database.getTotalSellers(); i++) {
            boolean sameNameWarning = false;
            String finalSellerName = null;

            String sellerName = null;
            while (true) {
                if (sameNameWarning) {
                    Util.println("The same name is not allowed: " + sellerName + ", write another name");
                }

                sellerName = Util.getUserString("Seller " + i + " Name: ");
                finalSellerName = Util.toTitleCase(sellerName);
                
                if  (sellersMap.containsKey(finalSellerName)) {
                    sameNameWarning = true;
                } else {
                    break;
                }
            }
            sellersMap.put(finalSellerName, new SellerModel(finalSellerName).toJson());
        }

        // baris kosong untuk spasi
        Util.emptySpace();

        // convert HashMap sellers menjadi json object
        database.setSellers(new JSONObject(sellersMap));
    }
}
