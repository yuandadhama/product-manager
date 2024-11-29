package com.view.viewfunctions;

import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.model.SellerModel;
import com.util.Util;

public class InfoSellerView {
    private InfoSellerView() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("unchecked")
    public static void show(JSONObject sellers) {
        Util.clearScreen();

        Util.println("== Info Sellers ==");
        Util.emptySpace();

        int totalMoney = 0;

        Set<String> sellerNames = sellers.keySet();
        int countSeller = 1;
        for (String sellerName : sellerNames) {
            Util.println(String.format("%d. %s", countSeller, sellerName));

            JSONObject seller = (JSONObject) sellers.get(sellerName);
            Set<String> packages = seller.keySet();

            int countPackage = 1;
            int sellerMoney = 0;
            for (String packageString : packages) {
                JSONArray packageObject = (JSONArray) seller.get(packageString);

                StringBuilder result = new StringBuilder();

                // Loop through the JSONArray and append each element to the StringBuilder
                int countCostumer = 0;
                for (int i = 0; i < packageObject.size(); i++) {
                    result.append(packageObject.get(i)); // Assuming the elements are strings
                    if (i < packageObject.size() - 1) {
                        result.append(", "); // Add comma between elements
                    }
                    countCostumer++;
                }

                // Convert StringBuilder to String
                String customersName = result.toString();

                switch (countPackage) {
                    case 1 -> sellerMoney += (8000 * countCostumer);
                    case 2 -> sellerMoney += (15000 * countCostumer);
                }

                Util.println(String.format("   - %s (%s): %s ", packageString, countCostumer, customersName));

                countPackage++;
            }
            totalMoney += sellerMoney;

            Util.println(String.format("%s's Money: %d", sellerName, sellerMoney));
            Util.emptySpace();
            countSeller++;
        }
        Util.println("Total Money: " + totalMoney);
        Util.emptySpace();
        Util.getUserInt("input any int to get back (larger than zero): ");
    }
}
