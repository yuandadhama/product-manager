package com.controller.addcustomer.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.model.DatabaseModel;

public class ExecuteData {
    private ExecuteData() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, int selectedPackage, String selectedSeller, String customerName) {
        int revenue = databaseModel.getRevenue();
        int quantityProduct = databaseModel.getProductQuantity();
        JSONObject sellersDb = new JSONObject(databaseModel.getSellers());
        
        switch (selectedPackage) {
            case 1 -> {
                revenue += 8000;
                quantityProduct -= 1;
                setCalculation(databaseModel, revenue, quantityProduct);
                setCustomerData(databaseModel, sellersDb, selectedSeller, customerName, "package-one");
            }
            case 2 -> {
                revenue += 15000;
                quantityProduct -= 2;
                setCalculation(databaseModel, revenue, quantityProduct);
                setCustomerData(databaseModel, sellersDb, selectedSeller, customerName, "package-two");
            }
        }
    }
    private static void setCalculation(DatabaseModel databaseModel, int revenue, int quantityProduct) {

        databaseModel.setRevenue(revenue);
        databaseModel.setProductQuantity(quantityProduct);

        int capital = databaseModel.getCapital();
        int newRevenue = databaseModel.getRevenue();

        if (newRevenue > capital) {
            databaseModel.setProfit(newRevenue - capital);
        }
    }

    @SuppressWarnings("unchecked")
    private static void setCustomerData(DatabaseModel databaseModel, JSONObject sellersDb, String sellerName, String customerName, String packageString) {

        // ambil package 
        JSONObject sellerObject = (JSONObject) sellersDb.get(sellerName);
        JSONArray packageArray = (JSONArray) sellerObject.get(packageString);
 
        // tambahkan data customer di package data
        packageArray.add(customerName);
    
        // tambahkan package ke database seller
        sellersDb.put(sellerName, sellerObject);

        // simpan data JSONObject di database model
        databaseModel.setSellers(sellersDb);
    }
}
