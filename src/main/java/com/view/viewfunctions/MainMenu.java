package com.view.viewfunctions;

import com.controller.AppController;
import com.util.Util;

public class MainMenu {
    private MainMenu() {
        throw new IllegalStateException("Utility class");
    }
    public static void show(AppController controller) {
        boolean showWarning = false;
        int option = 0;
        while (true) {
            // Clear screen and display menu
            Util.clearScreen();
            Util.println("Main Menu:");
            Util.emptySpace();
            Util.println("(1) Create New Field");
            Util.println("(2) Show Existing Field");
            Util.println("(3) Exit");
    
            // Get user input
            if (showWarning) {
                Util.emptySpace();
                Util.println("Invalid option: " + option + ", Please choose 1, or 2, 3");
            }

            Util.emptySpace();
            option = Util.getUserInt("Choose Option: ");
    
            // Handle menu options
            switch (option) {
                case 1 -> controller.createNewField();
                case 2 -> controller.showExistingField(true);
                case 3 -> {
                    Util.println("Exiting Application...");
                    return; // Exit the method and loop
                }
                default -> {
                    showWarning = true;
                }
            }
        }
    }
}
