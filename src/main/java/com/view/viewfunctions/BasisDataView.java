package com.view.viewfunctions;

import com.utils.Util;

public class BasisDataView {
    private BasisDataView() {
        throw new IllegalStateException("Utility class");
    }
    public static void show(int capital, int product, int totalSellers) {
        Util.clearScreen();

        // menampilkan data dari function setData
        System.out.println("== Creating New DB ==");
        System.out.println("Capital: " + capital);
        System.out.println("Products: " + product);
        System.out.println("Total Seller: " + totalSellers);

        // empty space
        System.out.println();
    }
}
