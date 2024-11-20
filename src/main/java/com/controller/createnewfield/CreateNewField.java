package com.controller.createnewfield;

import java.io.IOException;

import com.controller.AppController;
import com.model.DatabaseModel;
import com.utils.Util;
import com.view.AppView;

public class CreateNewField {
    private CreateNewField() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {
        // set data awal
        SetData.function(databaseModel);

        // tampilkan basis data (modal, jumlah product, total seller)
        view.basisData(databaseModel.getCapital(), databaseModel.getProduct(), databaseModel.getTotalSellers());

        // inisiasi nama-nama seller, berdasarkan total seller
        InitializeSeller.function(databaseModel);

        // tanya apakah data sudah benar -> buat file baru
        if (Util.getYesOrNo(databaseModel.getDbFileName() + " is created, do you want to open it?")) {
            CreateFile.function(databaseModel);
        } else {
            return;
        }

        // menulis data yang sudah di input ke dalam file
        try {
            WriteData.function(databaseModel);
        } catch (IOException e) {
            System.out.println("Cannot write the data");
            e.printStackTrace();
        }

        // tanya user apakah ingin membuka file yang dibuat
        if (Util.getYesOrNo(databaseModel.getDbFileName() + " is created, do you want to open it?")) {
            // view.showfield()
            System.out.println("file is opened");
        } else {
            view.mainMenu(new AppController());
        }
    }
}
