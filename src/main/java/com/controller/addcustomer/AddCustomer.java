package com.controller.addcustomer;

import com.controller.AppController;
import com.controller.addcustomer.utility.*;
import com.controller.common.ConvertModelToField;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class AddCustomer {
    private AddCustomer() {
        throw new IllegalStateException("Utility class");
    }
    public static void function(DatabaseModel databaseModel, AppView view) {
        while (true) {
            int selectedPackage = SelectPackage.function(databaseModel, view);
            String selectedSeller = SelectSeller.function(databaseModel, view);
        
            Util.emptySpace();
            String customerName = Util.toTitleCase(Util.getUserString("Input Customer Name: "));
        
            Util.emptySpace();
            if (Util.getYesOrNo("Are you sure with the data?")) {
                ExecuteData.function(databaseModel, selectedPackage, selectedSeller, customerName);
                break;
            } 
        }
        
        ConvertModelToField.function(databaseModel);  
        
        new AppController().showExistingField(false);
    }   
}
