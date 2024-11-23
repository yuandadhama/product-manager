package com.controller.addcustomer;

import org.json.simple.JSONObject;

import com.controller.AppController;
import com.controller.addcustomer.utility.*;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class AddCustomer {
    private AddCustomer() {
        throw new IllegalStateException("Utility class");
    }
    public static void function(DatabaseModel databaseModel, AppView view) {
        int selectedPackage = SelectPackage.function(view);
        JSONObject selectedSeller = SelectSeller.function(databaseModel, view);
        String customerName = Util.getUserString("Input Customer Name: ");

        ExecuteData.function(databaseModel, selectedPackage, selectedSeller, customerName);
        WriteDataToDb.function(databaseModel);  
        
        new AppController().showExistingField(false);
    }   
}
