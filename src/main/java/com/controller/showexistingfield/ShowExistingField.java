package com.controller.showexistingfield;

import com.controller.showexistingfield.utility.*;

import com.model.DatabaseModel;
import com.view.AppView;

public class ShowExistingField {
    private ShowExistingField() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view, boolean isShowingDbList) {
        // name file dengan .json
        String dbFile = null;

        // cek apakah menampilkan file untuk dipilih terlebih dahulu
        if (isShowingDbList) {
            // jika iya, ambil file db yang akan ditampilkan datanya
            dbFile = SelectDbFile.function(databaseModel, view);
        } else {
            // jika tidak, ambil file langsung dari modelnya
            dbFile = databaseModel.getDbFileName() + ".json";
        }

        databaseModel.setDbFilePath(databaseModel.getDbDirectoryPath() + dbFile);

        GetDataFromDb.function(databaseModel, dbFile);

        NextPlan.function(databaseModel, view);
    }
}
