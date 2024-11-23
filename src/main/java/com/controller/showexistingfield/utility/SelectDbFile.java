package com.controller.showexistingfield.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.DatabaseModel;
import com.util.Util;

public class SelectDbFile {
    private SelectDbFile() {
        throw new IllegalStateException("Utility class");
    }

    public static String function(DatabaseModel databaseModel) {
        // ambil database model directory nya
        File dirDataFile = new File(databaseModel.getDbDirectoryPath());

        // ambil file-file didalamnya
        File[] dirDataFileList = dirDataFile.listFiles();

        // ambil dan urutkan nama-nama file sesuai yang ditampilkan
        List<String> dataFileNames = new ArrayList<>();
        for (File file : dirDataFileList) {
            dataFileNames.add(file.getName());
        }
        Collections.sort(dataFileNames);

        boolean showWarning = false;
        while (true) {
            if (showWarning) {
                Util.println("Please choose appropiate database order: 1 - " + dataFileNames.size());
                Util.emptySpace();
            }

            // ambil input user
            Util.emptySpace();
            int desiredIndexFile = Util.getUserInt("Choose file database to open: ");
            Util.emptySpace();

            // cek apakah input user tidak ada di urutan
            if (desiredIndexFile < 1 || desiredIndexFile > dirDataFileList.length) {
                showWarning = true;
            } else {
                // set koneksi database
                String selectedFileName = dataFileNames.get(desiredIndexFile - 1);
                databaseModel.setDbFileName(selectedFileName.replaceAll(".json", ""));
                return selectedFileName;
            }
        }
    }
}
