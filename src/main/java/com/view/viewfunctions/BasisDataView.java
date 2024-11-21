package com.view.viewfunctions;

import com.util.Util;

public class BasisDataView {
    private BasisDataView() {
        throw new IllegalStateException("Utility class");
    }
    public static void show(int capital, int product, int totalSellers) {
        Util.clearScreen();

        // menampilkan data dari function setData
        Util.println("== Creating New DB ==");
        Util.println("Capital: " + capital);
        Util.println("Products: " + product);
        Util.println("Total Seller: " + totalSellers);

        // empty space
        Util.println("");
    }
}
