package com;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.util.CreateNewDBUtils;

public class App {
    // database filepath
    private String databaseFileName;
    private String dbDirectoryPath;
    private String dbFilePath;
    public String getDbFilePath() {
        return dbFilePath;
    }

    private int totalSellers;
    private int capital;
    private int product;
    private int revenue;
    private int profit;
    public int getTotalSellers() {
        return totalSellers;
    }
    public int getCapital() {
        return capital;
    }
    public int getProduct() {
        return product;
    }
    public int getRevenue() {
        return revenue;
    }
    public int getProfit() {
        return profit;
    }

    public void setTotalSellers(int totalSellers) {
        this.totalSellers = totalSellers;   
    }
    public void setCapital(int capital) {
        this.capital = capital;
    }
    public void setProduct(int product) {
        this.product = product;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
    public void setProfit(int profit) {
        this.profit = profit;
    }

    @SuppressWarnings("unchecked")
    public void createField() {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        CreateNewDBUtils createNewDBUtils = new CreateNewDBUtils(); 

        // insisaisi data awal
        createNewDBUtils.setData(this);
        
        createNewDBUtils.showData(this);
        
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

        // set alamat folder dan file untuk db yang akan ditampilkan
        dbDirectoryPath = "src/main/resources/database/";

        // listing DB files in database directory
        File dirDataFile = new File(dbDirectoryPath);
        File[] dirDataFileList = dirDataFile.listFiles();
        List<String> dbNamesList = new ArrayList<>();
        if (dirDataFileList != null) {
            int order = 1;
            for (File file : dirDataFileList) {
                String fileName = file.getName().replaceAll(".json", "");
                System.out.println("("+ order +") " + fileName);
                dbNamesList.add(fileName);
                order++;
            } 
        } else {
            System.out.println("There is no DB list, make a new DB field first");
        }
       
        // select DB files in database directory
        System.out.print("Choose DB: ");
        int desiredIndexFile = input.nextInt() - 1;
        databaseFileName = dbNamesList.get(desiredIndexFile);
        dbFilePath = dbDirectoryPath + databaseFileName + ".json";

        // baca file json yang dipilih
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
        input.close();
    }

    // @SuppressWarnings("unchecked")
    // private void updateDatabase(String dbDirectoryPath, String dbFilePath) {
    //     // this function is made to update selected connected db.json whenever this app field data is changed
    //     // the algorithm is just get this current data field and store to db

    //     // buat file json sebagai db baru, input data ke json
    //     JSONObject db = new JSONObject();

    //     db.put("capital", capital);
    //     db.put("product", product);
    //     db.put("revenue", revenue);
    //     db.put("profit", profit);


    //     // cek folder, buat folder jika belum ada
    //     File directory = new File(dbDirectoryPath);
    //     if (!directory.exists()) {
    //         directory.mkdirs();
    //     }

    //     // simpan ke file json
    //     try (FileWriter fileWriter = new FileWriter(dbFilePath)) {
    //         fileWriter.write(db.toString());
    //         fileWriter.flush();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    class Seller {
        private String name;
        private JSONArray packageOne = new JSONArray();
        private JSONArray packageTwo = new JSONArray();
        public Seller(String name) {
            this.name = name;
        }

        public JSONObject toJson() {
            HashMap<String, JSONArray> map = new HashMap<String, JSONArray>();
            map.put("package-one", packageOne);
            map.put("package-two", packageTwo);
            JSONObject jsonObject = new JSONObject(map);
            return jsonObject;
        }
    }
}
