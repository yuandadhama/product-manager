package com.controller;

import com.controller.addcustomer.AddCustomer;
import com.controller.createnewfield.CreateNewField;
import com.controller.showexistingfield.ShowExistingField;
import com.model.DatabaseModel;

import com.view.AppView;

public class AppController {
    private static final DatabaseModel databaseModel = new DatabaseModel();
    private static final AppView appView = new AppView();
    public void createNewField() {
        CreateNewField.function(databaseModel, appView);
    }

    /**
     * show data from database model
     * 
     * @param isShowingDbList true, showing db list to open. false, showing data field directly.
     */
    public void showExistingField(boolean isShowingDbList) {
        ShowExistingField.function(databaseModel, appView, isShowingDbList);
    }

    public void addCustomer() {
        AddCustomer.function(databaseModel, appView);
    }

    public void restock() {
        
    }

    public void infoSeller() {

    }
}

