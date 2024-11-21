package com;

import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.util.AppUtils.CreateNewDbUtils;

public class App {
    // database conncetion field
    private final String dbDirectoryPath = "src/main/resources/database/";
    private String databaseFileName;
    private String dbFilePath;
    
    // application field
    private int totalSellers;
    private int capital;
    private int product;
    private int revenue;
    private int profit;

    // sellers field
    private JSONObject sellers;

    public String getDatabaseFileName() {
        return databaseFileName;
    }
    public String getDbDirectoryPath() {
        return dbDirectoryPath;
    }
    public String getDbFilePath() {
        return dbFilePath;
    }
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
    public JSONObject getSellers() {
        return sellers;
    }

    public void setDbFilePath(String dbFilePath) {
        this.dbFilePath = dbFilePath;
    }
    public void setDatabaseFileName(String databaseFileName) {
        this.databaseFileName = databaseFileName;
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

    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();
    
    public void createNewDb() {
        
        // insisaisi data awal, modal, jumlah produk, total seller
        CreateNewDbUtils.setData(this, input, menu);
        
        // tampilkan data yang baru saja di input
        CreateNewDbUtils.showInputedData(this, input, menu);
        
        // inisiasi jumlah seller dan nama sellers
        sellers = CreateNewDbUtils.initializeSeller(this, input);

        if (!menu.getYesOrNo("Are you sure with the data")) {
            menu.main(this);

            // empty space
            System.out.println();
            return;
        }

        // pembuatan file db baru untuk kelola data
        try {
            CreateNewDbUtils.createDbFile(this);
            CreateNewDbUtils.writeDbFile(this);
        } catch (Exception e) {
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
// 
    public void showsField() {
        // set alamat folder dan file untuk db yang akan ditampilkan

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
}
