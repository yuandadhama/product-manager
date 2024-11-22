package com.controller.showexistingfield;

import com.controller.showexistingfield.utility.*;

import com.model.DatabaseModel;
import com.view.AppView;

public class ShowExistingField {
    private ShowExistingField() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {

        // cek dan tampilkan data yang ada
        ShowDbFileNames.function(databaseModel, view);

        // ambil file db yang akan ditampilkan datanya
        String dbFile = SelectDbFile.function(databaseModel);

        GetDataFromDb.function(databaseModel, dbFile);

        ShowDataDbFIle.function(databaseModel, view);

        NextPlan.function(view);
    }
}

