package com.controller.createnewfield;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.model.DatabaseModel;

public class CreateFile {
    private CreateFile() {
        throw new IllegalStateException("Utility class");
    }

    private static final String FILE_EXTENSION = ".json";
    public static void function(DatabaseModel databaseModel) {
        // title
        System.out.println("== Creating File Databse ==");

        // field untuk menampung nama file yang sudah di format
        String dbName = generateName(databaseModel);

        // buat file baru dengan format yang telah otomatis dibuat
        File file = new File(databaseModel.getDbDirectoryPath(), dbName + FILE_EXTENSION);

        // tampilkan nama file yang akan dibuat
        System.out.println("Your New Database Filename: " + dbName);

        // set field di model agar sesuai dengan file database yang terkoneksi
        databaseModel.setDbFileName(dbName);
        databaseModel.setDbFilePath(file.getAbsolutePath());

        // empty space
        System.out.println();
    }

    private static String generateName(DatabaseModel databaseModel) {
        // set nama untuk file database berdasarkan tanggal pembuatan
        LocalDate date = LocalDate.now();
        String baseFileName = String.format("DB-%d-%d-%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());

        // cek apakah directory ada, jika tidak buat
        File dirDataFile = new File(databaseModel.getDbDirectoryPath());
        if (!dirDataFile.exists()) {
            dirDataFile.mkdir();
        }

        // ambil directory database
        File[] listDirectoryFiles = dirDataFile.listFiles();

        // jika direcory kosong, set nama file = baseFileName
        if (listDirectoryFiles == null || listDirectoryFiles.length == 0) {
            return baseFileName;
        }

        // ambil nama-nama files di directory
        List<String> existingFileNames = new ArrayList<>();
        for (File file : listDirectoryFiles) {
            existingFileNames.add(file.getName());
        }
        
        // ketika ada nama yang sama, sesuikan nama index duplikat
        int indexDuplication = 2;
        while (existingFileNames.contains(listDirectoryFiles + FILE_EXTENSION)) {
            baseFileName = String.format("%s_(%d)", baseFileName, indexDuplication);
            indexDuplication++;
        }

        return baseFileName;
    }
}
