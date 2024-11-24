package com.controller.restock.utility;

import com.model.DatabaseModel;
import com.util.Util;

public class AddStock {
    private AddStock() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel) {
        Util.clearScreen();
        Util.println("== Restocking ==");

        databaseModel.setCapital(databaseModel.getCapital() + Util.getUserInt("Input capital: "));
        databaseModel.setProductQuantity(databaseModel.getProductQuantity() + Util.getUserInt("Input product quantity: "));
    }
}
