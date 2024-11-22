package com.view.viewfunctions;

import com.util.Util;

public class PackagesView {
    private PackagesView() {
        throw new IllegalStateException("Utility class");
    }

    public static void show() {
        Util.clearScreen();
        // Code to display the add customer view
        Util.println("Which package?");
        Util.println("(1) Buy 1 product | 8k");
        Util.println("(2) Buy 2 products | 15k");
    }
}
