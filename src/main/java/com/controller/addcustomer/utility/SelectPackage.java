package com.controller.addcustomer.utility;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class SelectPackage {
    private SelectPackage() {
        throw new IllegalStateException("Utility class");
    }

    public static int function(DatabaseModel databaseModel, AppView view) {
        boolean showWarning = false;
        boolean outOfProduct = false;
        int choosenPackage = 0;

        while (true) {
            view.packagesView();
            
            if (showWarning) {
                Util.println("Invalid option, please choose a valid package 1 - 2");
            } 

            if (outOfProduct) {
                Util.println("Not enough products in stock for package 2");
            }

            choosenPackage = Util.getUserInt("Choose package: ");

            outOfProduct = ((databaseModel.getProductQuantity() == 1 && choosenPackage == 2) ? true : false);

            // jika opsi yang dipilih tidak di opsi package yang ditampilkan 
            if (choosenPackage > 2 ) {

                // tampilkan pesan peringatan
                showWarning = true;
                continue;
            } 

            // jika product tidak cukup untuk package yang dipilih
            if (outOfProduct) {
                continue;
            }

            // jika semua data sudah valid, kembalikan datanya
            return choosenPackage;
        }
    }
}
