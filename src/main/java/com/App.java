package com;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {
    // database filepath
    private String databaseFileName;
    private String dbDirectoryPath;
    private String dbFilePath;
    public String getDbFilePath() {
        return dbFilePath;
    }

    private int totalSellers;
    // modal
    private int capital;
    public int getCapital() {
        return capital;
    }
    public void setCapital(int capital) {
        this.capital = capital;
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    // produk
    private int product;
    public int getProduct() {
        return product;
    }
    public void setProduct(int product) {
        this.product = product;
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    // pendapatan
    private int revenue;
    public int getRevenue() {
        return revenue;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
        updateDatabase(dbDirectoryPath, dbFilePath);
    }

    // untung
    private int profit;
    public int getProfit() {
        return profit;
    }
    public void setProfit(int profit) {
        this.profit = profit;
        updateDatabase(dbDirectoryPath, dbFilePath);
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

        System.out.print("Input how many sellers: ");
        totalSellers = input.nextInt();

        // menu hasil
        System.out.println("capital: " + capital);
        System.out.println("Products: " + product);
        System.out.println("Total Seller: " + totalSellers + " (It can be added later)");

        // data untuk input seller, initializeSeller()
        JSONObject sellers = new JSONObject();
        System.out.println("Input Sellers Data");
        for (int i = 0; i < totalSellers; i++) {
            System.out.print("Seller " + (i + 1) + " Name: ");
            String sellerName = input.next();

            Seller seller = new Seller(sellerName);
            sellers.put(sellerName, seller.toJson());
        }
        // pembuatan db untuk kelola data
        System.out.print("Input file database name (DB-<dd-mm-yyyy>): ");
        databaseFileName = input.next();

        // set alamat folder untuk db
        dbDirectoryPath = "src/main/resources/database/";
        dbFilePath = dbDirectoryPath + databaseFileName + ".json";

        // yakinkan user dengan data yang akan diinput
        if (!menu.getYesOrNo("Are you sure with the data")) {
            menu.main(this);
            input.close();
            return;
        }

          // buat file json sebagai db baru, input data ke json
          JSONObject db = new JSONObject();

          db.put("capital", capital);
          db.put("product", product);
          db.put("revenue", revenue);
          db.put("profit", profit);  
          db.put("totalSellers", totalSellers);
          db.put("sellers", sellers);
  
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
        // this function is made to update selected connected db.json whenever this app field data is changed
        // the algorithm is just get this current data field and store to db

        // buat file json sebagai db baru, input data ke json
        JSONObject db = new JSONObject();

        db.put("capital", capital);
        db.put("product", product);
        db.put("revenue", revenue);
        db.put("profit", profit);


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

    class Seller {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        private JSONArray packageOne = new JSONArray();
        public JSONArray getpackageOne () {
            return packageOne;
        }
        @SuppressWarnings("unchecked")
        public void addpackageOne (String customerName) {
            packageOne.add(customerName);
            updateDatabase(dbDirectoryPath, dbFilePath);
        }
    
        private JSONArray packageTwo = new JSONArray();
        public JSONArray getpackageTwo () {
            return packageTwo;
        }
        @SuppressWarnings("unchecked")
        public void addpackageTwo (String customerName) {
            packageTwo.add(customerName);
            updateDatabase(dbDirectoryPath, dbFilePath);
        }

        public Seller(String name) {
            this.name = name;
        }

        @SuppressWarnings("unchecked")
        public JSONObject toJson() {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("packageOne", packageOne);
            jsonObject.put("packageTwo", packageTwo);
            return jsonObject;
        }
    }
}
