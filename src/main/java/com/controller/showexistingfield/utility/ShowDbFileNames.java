package com.controller.showexistingfield.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.controller.AppController;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class ShowDbFileNames {
    private ShowDbFileNames() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {
        // ambil database model directory nya
        File dirDataFile = new File(databaseModel.getDbDirectoryPath());

        // ambil file-file didalamnya
        File[] dirDataFileList = dirDataFile.listFiles();

        if (dirDataFileList != null) {
            List<String> dataFileNames = new ArrayList<>();

            for (File file : dirDataFileList) {
                dataFileNames.add(file.getName());
            }

            Collections.sort(dataFileNames);
            // title 
            Util.clearScreen();
            Util.println("== Database List ==");
            Util.emptySpace();

            int order = 1;
            for (String fileName : dataFileNames) {
                // hilangkan extension file
                fileName = fileName.replaceAll(".json", "");

                // tampilkan nama-nama file data yang sudah diformat
                Util.println(String.format("(%d) %s", order, fileName));

                // sesuaikan urutan nama file yang akan ditampilkan selanjutnya
                order++;
            } 
        } else {
            Util.println("There is no DB list, make a new DB field first");
            Util.emptySpace();

            if (Util.getYesOrNo("Do you want to create new field?")) {
                new AppController().createNewField();
            } else {
                view.mainMenu(new AppController());
            }
        }
    }
}
