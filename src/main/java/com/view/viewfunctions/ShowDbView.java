package com.view.viewfunctions;

import com.util.Util;

public class ShowDbView {
    private ShowDbView() {
        throw new IllegalStateException("Utility class");
    }

    public static void show(String dbName, int capital, int quantityProduct, int revenue, int profit) {
        Util.clearScreen();

        Util.println("== " + dbName + " ==");
        Util.emptySpace();
        
        Util.println("(1) Capital: " + capital);
        Util.println("(2) Quantity of Products: " + quantityProduct);
        Util.println("(3) Revenue: " + revenue);
        Util.println("(4) Profit: " + profit);
        Util.emptySpace();
    }
}
