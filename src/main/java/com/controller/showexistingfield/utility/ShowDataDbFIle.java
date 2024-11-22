package com.controller.showexistingfield.utility;

import com.model.DatabaseModel;
import com.view.AppView;

public class ShowDataDbFIle {
    private ShowDataDbFIle() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {

        String dbFileNames = databaseModel.getDbFileName();
        int capital = databaseModel.getCapital();
        int quantityProduct = databaseModel.getProduct();
        int revenue = databaseModel.getRevenue();
        int profit = databaseModel.getProfit();

        view.showDbView(dbFileNames, capital, quantityProduct, revenue, profit);
    }
}
