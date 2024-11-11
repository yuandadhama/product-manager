package com;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {
    // modal
    private int capital = 0;
    public int getCapital() {
        return capital;
    }
    public void setCapital(int capital) {
        this.capital = capital;
    }

    // produk
    private int product = 0;
    public int getProduct() {
        return product;
    }
    public void setProduct(int product) {
        this.product = product;
    }

    // pendapatan
    private int revenue = 0;
    public int getRevenue() {
        return revenue;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    // untung
    private int profit = 0;
    public int getProfit() {
        return profit;
    }
    public void setProfit(int profit) {
        this.profit = profit;
    }

    private String[] customerPackageOne = {};
    public List<String> getCustomerPackageOne() {
        return new ArrayList<>(Arrays.asList(customerPackageOne));
    }
    public void setCustomerPackageOne(List<String> updatedStrings) {
        this.customerPackageOne= updatedStrings.toArray(new String[0]);
    }


    private String[] customerPackageTwo = {};
    public List<String> getCustomerPackageTwo() {
        return new ArrayList<>(Arrays.asList(customerPackageTwo));
    }
    public void setCustomerPackageTwo(List<String> updatedStrings) {
        this.customerPackageTwo = updatedStrings.toArray(new String[0]);
    }

    @SuppressWarnings("unchecked")
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
            menu.main(this);
            input.close();
            return;
        }

        // pembuatan db untuk kelola data
        System.out.print("Input file database name (DB-<dd-mm-yyyy>): ");
        String databaseName = input.next();

        // alamat folder untuk db
        String directoryPath = "src/main/resources/database/";
        String dbFilePath = directoryPath + databaseName + ".json";

        // buat file json sebagai db baru, input data ke json
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
            fileWriter.write(db.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // apakah user ingin membuka file yang baru saja dibuat?
        if (menu.getYesOrNo(databaseName + " is created, do you want to open it?")) {
            menu.dataFileMenu(this, databaseName);
        } else {
            menu.main(this);
        }

        input.close();
    }

    public void showsField() {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();

        System.out.print("Input database file to open (DB-<dd-mm-yyyy>): ");
        String databaseName = input.next();

        // alamat folder untuk db
        String directoryPath = "src/main/resources/database/";
        String dbFilePath = directoryPath + databaseName + ".json";

        // cek file json, jika file ada, baca file json
        if (new File(dbFilePath).exists()) {
            try (FileReader fileReader = new FileReader(dbFilePath)) {
                JSONParser jsonParser = new JSONParser();
                try {
                    JSONObject db = (JSONObject) jsonParser.parse
                    (fileReader);   

                    // ambil data dari file database untuk ditampilkan
                    capital = (int) db.get("capital");
                    System.out.println(capital);

                    product = (int) db.get("product");
                    System.out.println(product);
                    revenue = (int) db.get("revenue");
                    profit = (int) db.get("profit");
                } catch (Exception e) {
                    e.printStackTrace();
                }                
                // menu.dataFileMenu(this, databaseName);
            } catch (IOException e) {
                System.out.println("test error muncul");
                e.printStackTrace();
            }
        } else {
            System.err.println("Database " + databaseName + " not found");
            if (menu.getYesOrNo("Do you want to continue?")) {
                menu.main(this);
            } else {
                System.exit(0);
            }
        }

        input.close();
    }
}
