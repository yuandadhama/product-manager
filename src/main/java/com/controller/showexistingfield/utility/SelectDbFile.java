package com.controller.showexistingfield.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.controller.AppController;
import com.model.DatabaseModel;
import com.util.Util;
import com.view.AppView;

public class SelectDbFile {
    private SelectDbFile() {
        throw new IllegalStateException("Utility class");
    }

    public static String function(DatabaseModel databaseModel, AppView view) {

        // ambil database model directory nya
        File dirDataFile = new File(databaseModel.getDbDirectoryPath());

        // ambil file-file didalamnya
        File[] dirDataFileList = dirDataFile.listFiles();

        // handle jika directory file kosong
        if (dirDataFileList == null || dirDataFileList.length == 0) {
            emptyDirectoryHandler();
            return null;
        }

        Map<String, String> fileKeyMap = new HashMap<>();
        List<String> dataFiles = new ArrayList<>();

        for (File file : dirDataFileList) {
            try {
                // ambil file db dari directory dan baca file nya menjadi JSONObject
                JSONObject data = Util.readFileToJson(file.getAbsolutePath());

                // ambil uniqueKey
                String uniqueKey = (String) data.get("uniqueKey");
                String fileNameJson = file.getName();

                dataFiles.add(fileNameJson);
                fileKeyMap.put(fileNameJson, uniqueKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // urutkan nama nama file berdasarkan unique key
        dataFiles.sort(Comparator.comparing(fileKeyMap::get));

        // tampilkan nama nama file nya
        view.dbFileNamesView(dataFiles);

        // pilih nama file dan ambil nama file yang dipilih
        String selectedFile = selectFileName(dataFiles);

        // hilangkan format json untuk set koneksi nama file database model nya 
        databaseModel.setDbFileName(selectedFile.replaceAll(".json", ""));

        // ambil nama file yang dipilih masih dengan format .json
        return selectedFile;

    }

    private static String selectFileName(List<String> dataFileNames) {
        boolean showWarning = false;
        while (true) {
            if (showWarning) {
                Util.println("Please choose appropiate database order: 1" + (dataFileNames.size() > 1 ? " - " + dataFileNames.size() : ""));
            }

            // ambil input user
            Util.emptySpace();
            int desiredIndexFile = Util.getUserInt("Choose file database to open: ");

            // cek apakah input user tidak ada di urutan
            if (desiredIndexFile < 1 || desiredIndexFile > dataFileNames.size()) {
                showWarning = true;
            } else {
                return dataFileNames.get(desiredIndexFile - 1);
            }
        }
    }

    private static void emptyDirectoryHandler() {
        Util.println("There is no DB list, make a new DB field first");
        Util.emptySpace();

        if (Util.getYesOrNo("Do you want to create new field?")) {
            new AppController().createNewField();
        } else {
            new AppView().mainMenu(new AppController());
        }
    }
}
