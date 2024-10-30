package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

public class App {
    // modal awal
    private int initialCapital = 0;

    // total prodcut awal
    private int initialProduct = 0;

    // laba kotor
    private int revenue = 0;

    // untung
    private int profit = 0;

    public void createField() {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();

        // inisiasi pembuatan file baru
        System.out.print("Input Capital: ");
        initialCapital = input.nextInt();

        System.out.print("Input Total Product: ");
        initialProduct = input.nextInt();

        // menu hasil
        System.out.println("Capital: " + initialCapital);
        System.out.println("Products: " + initialProduct);

        // yakinkan user dengan data yang akan diinput
        boolean isUserYes = menu.isYes("Are you sure with the data");

        if (!isUserYes) {
            menu.clearScreen();
            menu.main(this);

            input.close();
            return;
        }

        // pembuatan db untuk kelola data
        System.out.print("Masukkan Nama Database (DB-<dd-mm-yy>): ");
        String databaseName = input.next();

        // alamat folder untuk db
        String directoryPath = "src/main/resources/database/";
        String dbFilePath = directoryPath + databaseName + ".json";

        // buat json sebagai db, input data ke json
        JSONObject db = new JSONObject();
        db.put("initialCapital", initialCapital);
        db.put("initialProduct", initialProduct);

        // cek folder, buat folder jika belum ada
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // simpan ke file json
        try (FileWriter fileWriter = new FileWriter(dbFilePath)) {
            fileWriter.write(db.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        menu.clearScreen();
        menu.main(this);

        input.close();
    }

    public void updateField() {
        System.out.println("Next action update field");
    }
}
