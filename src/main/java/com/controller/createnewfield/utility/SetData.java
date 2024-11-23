package com.controller.createnewfield.utility;

import com.model.DatabaseModel;
import com.util.Util;

public class SetData {
    private SetData() {
        throw new IllegalStateException("Utility class");
    }
    public static void function(DatabaseModel database) {
        // clean screen
        Util.clearScreen();

        // title
        Util.println("== Creating New Database ==");

        // Input 3 basis data
        database.setCapital(Util.getUserInt("Input Capital: "));
        database.setProductQuantity(Util.getUserInt("Input Total Product: "));
        database.setTotalSellers(Util.getUserInt("Input how many sellers: "));

        // reset data data lain di database model
        database.setProfit(0);
        database.setRevenue(0);
    }
}
