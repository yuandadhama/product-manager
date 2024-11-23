package com.controller.showexistingfield.utility;

import com.controller.AppController;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class NextPlan {
    private NextPlan() {
        throw new IllegalStateException("Utility class");
    }
    public static void function(DatabaseModel databaseModel, AppView view) {
        while (true) {
            int option = Util.getUserInt("Choose option: ");
    
            if (option == 1) {
                new AppController().addCustomer();
            } else if (option == 2) {
                new AppController().restock();
            } else if (option == 3) {
                new AppController().infoSeller();
            } else if (option == 4) {
                view.mainMenu(new AppController());
            } else {
                Util.println("Unexpected Value: " + option + " choose the appropriate option");
            }
        }
    }
}
