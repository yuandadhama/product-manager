package com.controller;

import com.controller.addcustomer.AddCustomer;
import com.controller.createnewfield.CreateNewField;
import com.controller.showexistingfield.ShowExistingField;
import com.model.DatabaseModel;
import com.view.AppView;

public class AppController {
    private final DatabaseModel databaseModel = new DatabaseModel();
    private final AppView appView = new AppView();
    public void createNewField() {
        CreateNewField.function(databaseModel, appView);
    }

    public void showExistingField() {
        ShowExistingField.function(databaseModel, appView);
    }

    public void addCustomer() {
        AddCustomer.function(databaseModel, appView);
    }

    public void restock() {

    }

    public void infoSeller() {

    }
}

