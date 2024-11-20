package com.controller.createnewfield;

import com.model.DatabaseModel;
import com.utils.Util;

public class SetData {
    private SetData() {
        throw new IllegalStateException("Utility class");
    }
    public static void function(DatabaseModel database) {
        // clean screen
        Util.clearScreen();

        // title
        System.out.println("== Creating New Database ==");

        // Input 3 basis data
        database.setCapital(promptForInt("Input Capital: "));
        database.setProduct(promptForInt("Input Total Product: "));
        database.setTotalSellers(promptForInt("Input how many sellers: "));
    }

    private static int promptForInt(String str) {
        System.out.print(str);
        return Util.getUserInt();
    }
}
