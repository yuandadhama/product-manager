package com;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

public class App {
    // modal
    private int capital = 0;

    public int getCapital() {
        return capital;
    }

    // produk
    private int product = 0;

    public int getProduct() {
        return product;
    }

    // laba kotor
    private int revenue = 0;

    public int getRevenue() {
        return revenue;
    }

    // untung
    private int profit = 0;

    public int getProfit() {
        return profit;
    }

    public void createField() {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();

        // inisiasi pembuatan file baru
        System.out.print("Input capital: ");
        capital = input.nextInt();

        System.out.print("Input Total Product: ");
        product = input.nextInt();

        // menu hasil
        System.out.println("capital: " + capital);
        System.out.println("Products: " + product);

        // yakinkan user dengan data yang akan diinput
        if (!menu.getYesOrNo("Are you sure with the data")) {
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
        db.put("capital", capital);
        db.put("product", product);
        db.put("revenue", revenue);
        db.put("profit", profit);

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

        if (menu.getYesOrNo(databaseName + " is created, do you want to open it?")) {
            menu.clearScreen();
            this.showsField();
        } else {
            menu.clearScreen();
            menu.main(this);
        }

        input.close();
    }

    public void showsField() {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();

        System.out.print("Masukkan Nama Database (DB-<dd-mm-yy>): ");
        String databaseName = input.next();

        // alamat folder untuk db
        String directoryPath = "src/main/resources/database/";
        String dbFilePath = directoryPath + databaseName + ".json";

        // cek file json, jika file ada, baca file json
        if (new File(dbFilePath).exists()) {
            try (FileReader fileReader = new FileReader(dbFilePath)) {
                JSONObject db = new JSONObject(fileReader);

                // tampilkan data
                menu.dataFileMenu(this, databaseName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Database " + databaseName + " not found");
        }

        input.close();
    }
}
