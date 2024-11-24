package com.controller.createnewfield.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.model.DatabaseModel;
import com.util.Util;

public class CreateFile {
    private CreateFile() {
        throw new IllegalStateException("Utility class");
    }

    private static final String FILE_EXTENSION = ".json";
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static String lastTimestamp = "";

    public static void function(DatabaseModel databaseModel) {
        // title
        Util.println("== Creating File Database = ");

        // field untuk menampung nama file yang sudah di format
        String dbName = generateName(databaseModel);

        // buat file baru dengan format yang telah otomatis dibuat
        File file = new File(databaseModel.getDbDirectoryPath(), dbName + FILE_EXTENSION);

        // tampilkan nama file yang akan dibuat
        Util.println("Your New Database Filename: " + dbName);

        // set field di model agar sesuai dengan file database yang terkoneksi
        databaseModel.setDbFileName(dbName);
        databaseModel.setDbFilePath(file.getAbsolutePath());

        // tambahkan kode unik file untuk sorting
        databaseModel.setUniqueKey(generateUniqueKey());

        // empty space
        Util.emptySpace();
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
        String finalFileName = baseFileName;

        while (existingFileNames.contains(finalFileName + FILE_EXTENSION)) {
            finalFileName = String.format("%s_(%d)", baseFileName, indexDuplication);
            indexDuplication++;
        }

        return finalFileName;
    }

    public static synchronized String generateUniqueKey() {
        // Get the current timestamp
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        // Reset counter if timestamp changes
        if (!timestamp.equals(lastTimestamp)) {
            counter.set(0);
            lastTimestamp = timestamp;
        }

        // Increment counter for uniqueness
        int count = counter.incrementAndGet();

        // Append counter to timestamp (ensures sortability)
        return timestamp + String.format("%03d", count);
    }

}
