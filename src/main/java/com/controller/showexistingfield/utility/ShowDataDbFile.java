package com.controller.showexistingfield.utility;

import com.model.DatabaseModel;
import com.view.AppView;

public class ShowDataDbFile {
    private ShowDataDbFile() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {

        String dbFileNames = databaseModel.getDbFileName();
        int capital = databaseModel.getCapital();
        int quantityProduct = databaseModel.getProductQuantity();
        int revenue = databaseModel.getRevenue();
        int profit = databaseModel.getProfit();

        view.showDbView(dbFileNames, capital, quantityProduct, revenue, profit);
    }
}
