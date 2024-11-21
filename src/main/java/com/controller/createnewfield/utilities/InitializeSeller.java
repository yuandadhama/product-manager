package com.controller.createnewfield.utilities;

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
            String sellerName = Util.getUserString("Seller " + i + " Name: ");
            sellersMap.put(sellerName, new SellerModel(sellerName).toJson());
        }

        // baris kosong untuk spasi
        Util.emptySpace();

        // convert HashMap sellers menjadi json object
        database.setSellers(new JSONObject(sellersMap));
    }
}
