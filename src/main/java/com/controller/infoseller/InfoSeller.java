package com.controller.infoseller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.model.DatabaseModel;
import com.model.SellerModel;
import com.util.Util;
import com.view.AppView;

public class InfoSeller {
    private InfoSeller() {
        throw new IllegalStateException("Utility Class");
    }

    @SuppressWarnings("unchecked")
    public static void function(DatabaseModel databaseModel, AppView view) {
        JSONObject sellers = databaseModel.getSellers();
        Set<String> sellerNames = sellers.keySet();

        int totalMoney = 0;
        for (String sellerName : sellerNames) {
            Util.println(sellerName);
            JSONObject seller = (JSONObject) sellers.get(sellerName);
            Set<String> packages = seller.keySet();

            for (String packageString : packages) {
                Util.getUserInt(packageString);
                JSONArray packageObject = (JSONArray) seller.get(packageString);
                Util.println(packageObject.toString());
            }



            Util.println("test 1");
        }
        Util.getUserInt("wait");
    }
}
