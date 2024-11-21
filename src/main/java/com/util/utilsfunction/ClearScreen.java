package com.util.utilsfunction;

import com.util.Util;

public class ClearScreen {
    private ClearScreen() {
        throw new IllegalStateException("Utility class");
    }
    public static void function() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Util.println("\033\143");
            }
        } catch (Exception e) {
            Util.println("Cannot clear the screen");
        }
    }
}
