package com.controller.createnewfield;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.model.DatabaseModel;
import com.utils.Util;
import com.utils.AppUtils.Seller;

public class InitializeSeller {
    private InitializeSeller() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel database) {
        HashMap<Object, JSONObject> sellersMap = new HashMap<>();

        System.out.println("== Sellers Data ==");
        for (int i = 1; i <= database.getTotalSellers(); i++) {
            System.out.print("Seller " + i + " Name: ");
            String sellerName = Util.getUserString();
            sellersMap.put(sellerName, new Seller(sellerName).toJson());
        }

        // baris kosong untuk spasi
        System.out.println();

        // convert HashMap sellers menjadi json object
        database.setSellers(new JSONObject(sellersMap));
    }
}
