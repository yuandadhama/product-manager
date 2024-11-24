package com.model;

import org.json.simple.JSONObject;

public class DatabaseModel {

    // database conncetion field
    private static final String DB_DIR_PATH = "src/main/resources/database/";
    private String dbFilePath;
    private String dbFileName;
    
    // application field
    private int totalSellers;
    private int capital;
    private int productQuantity;
    private int revenue;
    private int profit;

    // unique key file
    private String uniqueKey;

    // sellers field
    private JSONObject sellers;

    // getter
    public String getDbFileName() {
        return dbFileName;
    }
    public String getDbDirectoryPath() {
        return DB_DIR_PATH;
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
    public int getProductQuantity() {
        return productQuantity;
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
    public String getUniqueKey() {
        return uniqueKey;
    }

    // setter
    public void setDbFilePath(String dbFilePath) {
        this.dbFilePath = dbFilePath;
    }
    public void setDbFileName(String dbFileName) {
        this.dbFileName = dbFileName;
    }
    public void setTotalSellers(int totalSellers) {
        this.totalSellers = totalSellers;   
    }
    public void setCapital(int capital) {
        this.capital = capital;
    }
    public void setProductQuantity(int product) {
        this.productQuantity = product;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
    public void setProfit(int profit) {
        this.profit = profit;
    }
    public void setSellers(JSONObject sellers) {
        this.sellers = sellers;
    }
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
