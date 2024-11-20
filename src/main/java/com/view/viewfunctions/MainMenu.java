package com.view.viewfunctions;

import com.controller.AppController;
import com.utils.Util;

public class MainMenu {
    private MainMenu() {
        throw new IllegalStateException("Utility class");
    }
    public static void show(AppController controller) {
        Util.clearScreen();
        System.out.println("Main Menu:");
        System.out.println("(1) Create New Field");
        System.out.println("(2) Show Existed Field");

        System.out.print("Choose Option: ");
        int option = Util.getUserInt();

        switch (option) {
            case 1 -> controller.createNewField();
            case 2 -> controller.showExistingField();
            default -> {
                System.err.println("Unexpected Value: " + option + "choose the appropriate option");
            }
        }

    }
}
