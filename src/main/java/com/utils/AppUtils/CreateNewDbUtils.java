package com.utils.AppUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.App;
import com.Menu;

public class CreateNewDbUtils {
    public static void setData(App app, Scanner input, Menu menu) {
        // clean screen
        menu.clearScreen();

        // title
        System.out.println("== Creating New Database ==");

        // Input 3 basis data
        System.out.print("Input capital: ");
        app.setCapital(input.nextInt());

        System.out.print("Input Total Product: ");
        app.setProduct(input.nextInt());

        System.out.print("Input how many sellers: ");
        app.setTotalSellers(input.nextInt());
    }

    public static void showInputedData(App app, Scanner input, Menu menu) {
        // clean screen
        menu.clearScreen();

        // menampilkan data dari function setData
        System.out.println("== Creating New DB ==");
        System.out.println("Capital: " + app.getCapital());
        System.out.println("Products: " + app.getProduct());
        System.out.println("Total Seller: " + app.getTotalSellers());

        // empty space
        System.out.println();
    }

    public static JSONObject initializeSeller(App app, Scanner input) {
        // HashMap sellersMap, untuk menampung object seller
        HashMap<Object, JSONObject> sellersMap = new HashMap<Object, JSONObject>();

        System.out.println("== Sellers Data ==");
        for (int i = 0; i < app.getTotalSellers(); i++) {
            System.out.print("Seller " + (i + 1) + " Name: ");
            String sellerName = input.next();

            Seller seller = new Seller(sellerName);
            sellersMap.put(seller.getName(), seller.toJson());
        }

        // empty space
        System.out.println();

        // convert HashMap sellers menjadi json object
        JSONObject sellers = new JSONObject(sellersMap);
        return sellers;
    }

    public static void createDbFile(App app) {
        // title
        System.out.println("== Creating File Databse ==");

        // cek apakah directory ada, jika tidak buat
        File dirDataFile = new File(app.getDbDirectoryPath());
        if (!dirDataFile.exists()) {
            dirDataFile.mkdir();
        }

        // set nama untuk file database berdasarkan tanggal pembuatan
        LocalDate date = LocalDate.now();
        String baseFileName = String.format("DB-%d-%d-%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        final String FILE_EXTENSION = ".json";

        // field untuk menampung nama file yang sudah di format
        String dbName = null;

        // pembuatan format duplikasi jika ada nama yang sama
        File[] listDirectoryFiles = dirDataFile.listFiles();

        if (listDirectoryFiles == null || listDirectoryFiles.length == 0) {
            dbName = baseFileName;
        }

        List<String> existingFileNames = new ArrayList<>();
        for (File file : listDirectoryFiles) {
            existingFileNames.add(file.getName());
        }
        
        int indexDuplication = 2;
        while (existingFileNames.contains(listDirectoryFiles + FILE_EXTENSION)) {
            dbName = String.format("%s_(d)", baseFileName, indexDuplication);
            indexDuplication++;
        }

        // buat file baru dengan format yang telah otomatis dibuat
        File file = new File(dirDataFile, dbName + FILE_EXTENSION);

        // tampilkan nama file yang akan dibuat
        System.out.println("Your New Database Filename: " + dbName);

        // set field di aplikasi agar sesuai dengan database yang terkoneksi
        app.setDatabaseFileName(dbName);
        app.setDbFilePath(file.getAbsolutePath());

        // empty space
        System.out.println();
    }

    public static void writeDbFile(App app) throws IOException {
        // hashmap untuk membangun database
        HashMap<String, Object> appDataMap = new HashMap<String, Object>();

        // tambahkan data ke hashmap
        appDataMap.put("capital", app.getCapital());
        appDataMap.put("product", app.getProduct());
        appDataMap.put("revenue", app.getRevenue());
        appDataMap.put("profit", app.getProfit());
        appDataMap.put("totalSellers", app.getTotalSellers());
        appDataMap.put("sellers", app.getSellers());

        // convert hashmap menjadi object yang akan ditulis ke file
        JSONObject jsonObject = new JSONObject(appDataMap);

        // tulis ke file
        FileWriter fileWriter = new FileWriter(app.getDbFilePath());
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.close();
    }
}
