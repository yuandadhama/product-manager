package com;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {
    // database filepath
    private String databaseFileName;
    private String dbDirectoryPath;
    private String dbFilePath;

    // modal
    private int capital = 0;
    public int getCapital() {
        return capital;
    }
    public void setCapital(int capital) {
        this.capital = capital;
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    // produk
    private int product = 0;
    public int getProduct() {
        return product;
    }
    public void setProduct(int product) {
        this.product = product;
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    // pendapatan
    private int revenue = 0;
    public int getRevenue() {
        return revenue;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    // untung
    private int profit = 0;
    public int getProfit() {
        return profit;
    }
    public void setProfit(int profit) {
        this.profit = profit;
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    private JSONArray customerPackageOne = new JSONArray();
    public JSONArray getCustomerPackageOne () {
        return customerPackageOne;
    }
    @SuppressWarnings("unchecked")
    public void addCustomerPackageOne (String customerName) {
        customerPackageOne.add(customerName);
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    private JSONArray customerPackageTwo = new JSONArray();
    public JSONArray getCustomerPackageTwo () {
        return customerPackageTwo;
    }
    @SuppressWarnings("unchecked")
    public void addCustomerPackageTwo (String customerName) {
        customerPackageTwo.add(customerName);
        updateDatabase(dbDirectoryPath, dbFilePath);
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
            menu.main(this);
            input.close();
            return;
        }

        // pembuatan db untuk kelola data
        System.out.print("Input file database name (DB-<dd-mm-yyyy>): ");
        databaseFileName = input.next();

        // set alamat folder untuk db
        dbDirectoryPath = "src/main/resources/database/";
        dbFilePath = dbDirectoryPath + databaseFileName + ".json";

        updateDatabase(dbDirectoryPath, dbFilePath);
        
        // apakah user ingin membuka file yang baru saja dibuat?
        if (menu.getYesOrNo(databaseFileName + " is created, do you want to open it?")) {
            menu.dataFileMenu(this, databaseFileName);
        } else {
            menu.main(this);
        }

        input.close();
    }

    public void showsField() {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();

        System.out.print("Input database file to open (DB-<dd-mm-yyyy>): ");
        databaseFileName = input.next();

        // set alamat folder dan file untuk db yang akan ditampilkan
        dbDirectoryPath = "src/main/resources/database/";
        dbFilePath = dbDirectoryPath + databaseFileName + ".json";

        // cek file json, jika file ada, baca file json
        if (new File(dbFilePath).exists()) {
            try (FileReader fileReader = new FileReader(dbFilePath)) {
                JSONParser jsonParser = new JSONParser();
                try {
                    JSONObject db = (JSONObject) jsonParser.parse
                    (fileReader);   

                    // ambil data dari file database untuk ditampilkan
                    // convert and set data
                    Number capitalNumber = (Number) db.get("capital");
                    capital = capitalNumber.intValue();

                    Number productNumber = (Number) db.get("product");
                    product = productNumber.intValue();

                    Number revenueNumber = (Number) db.get("revenue");
                    revenue = revenueNumber.intValue();
                    
                    Number profitNumber = (Number) db.get("profit");
                    profit = profitNumber.intValue();

                    JSONObject customers = (JSONObject) db.get("customers");
                    customerPackageOne = (JSONArray) customers.get("package-one");
                    customerPackageTwo = (JSONArray) customers.get("package-two");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }                
                menu.dataFileMenu(this, databaseFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(databaseFileName + " not found");
            System.out.println(dbFilePath);
            if (menu.getYesOrNo("Do you want to continue?")) {
                menu.main(this);
            } else {
                System.exit(0);
            }
        }

        input.close();
    }

    @SuppressWarnings("unchecked")
    private void updateDatabase(String dbDirectoryPath, String dbFilePath) {
        // buat file json sebagai db baru, input data ke json
        JSONObject db = new JSONObject();

        db.put("capital", capital);
        db.put("product", product);
        db.put("revenue", revenue);
        db.put("profit", profit);

        // data customer
        JSONObject customers = new JSONObject();

        customers.put("package-one", customerPackageOne);
        customers.put("package-two", customerPackageTwo);
        db.put("customers", customers);


        // cek folder, buat folder jika belum ada
        File directory = new File(dbDirectoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // simpan ke file json
        try (FileWriter fileWriter = new FileWriter(dbFilePath)) {
            fileWriter.write(db.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
