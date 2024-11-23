package com.controller.createnewfield;

import com.controller.AppController;
import com.controller.createnewfield.utility.*;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class CreateNewField {
    private CreateNewField() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {
        while (true) {
            // set data awal
            SetData.function(databaseModel);
    
            // tampilkan basis data (modal, jumlah product, total seller)
            view.basisData(databaseModel.getCapital(), databaseModel.getProductQuantity(), databaseModel.getTotalSellers());
    
            // inisiasi nama-nama seller, berdasarkan total seller
            InitializeSeller.function(databaseModel);
    
            // tanya apakah data sudah benar -> buat file baru
            if (Util.getYesOrNo("Are you sure with the data?")) {
                CreateFile.function(databaseModel);
                break;
            } 
        }

        // menulis data yang sudah di input ke dalam file
        WriteData.function(databaseModel);

        // tanya user apakah ingin membuka file yang dibuat
        if (Util.getYesOrNo(databaseModel.getDbFileName() + " is created, do you want to open it?")) {
            new AppController().showExistingField(false);
        } else {
            // kembali ke main menu
            view.mainMenu(new AppController());
        }
    }
}
