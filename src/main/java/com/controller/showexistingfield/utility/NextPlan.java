package com.controller.showexistingfield.utility;

import com.controller.AppController;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class NextPlan {
    private NextPlan() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {
        boolean showWarning = false;
        while (true) {
            showField(databaseModel, view);

            if (showWarning) {
                Util.println("Cannot add customer, out of stock");
            }
            int option = Util.getUserInt("Choose option: ");

            if (option == 1) {
                if (databaseModel.getProductQuantity() > 0) {
                    new AppController().addCustomer();
                } else {
                    showWarning = true;
                }
            } else if (option == 2) {
                new AppController().restock();
            } else if (option == 3) {
                new AppController().infoSeller();
            } else if (option == 4) {
                view.mainMenu(new AppController());
            } else {
                Util.println("Unexpected Value: " + option + " choose the appropriate option");
            }
        }
    }

    private static void showField(DatabaseModel databaseModel, AppView view) {
        String dbFileNames = databaseModel.getDbFileName();
        int capital = databaseModel.getCapital();
        int quantityProduct = databaseModel.getProductQuantity();
        int revenue = databaseModel.getRevenue();
        int profit = databaseModel.getProfit();

        view.showDbView(dbFileNames, capital, quantityProduct, revenue, profit);
    }
}
