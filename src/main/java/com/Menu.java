package com;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Menu {
    Scanner input = new Scanner(System.in);

    public void main(App app) {
        this.clearScreen();
        System.out.println("Main Menu:");
        System.out.println("(1) Create New Field");
        System.out.println("(2) Show Existed Field");

        System.out.print("Choose Option: ");
        int option = input.nextInt();

        switch (option) {
            case 1 -> app.createNewDb();
            case 2 -> app.showsField();
            default -> {
                System.err.println("Unexpected Value: " + option + "choose the appropriate option");
                this.main(app);
            }
        }

    }

    public void dataFileMenu(App app, String databaseName) {
        this.clearScreen();
        System.out.println("========== " + databaseName + " ==========");
        System.out.println("Capital: " + app.getCapital());
        System.out.println("Product: " + app.getProductQuantity());
        System.out.println("Revenue: " + app.getRevenue());
        System.out.println("Profit: " + app.getProfit());
        System.out.println("========== Option ==========");
        System.out.println("(1) Add Customer");
        System.out.println("(2) Restock");
        System.out.println("(3) Info Seller");
        System.out.println("(4) Back to Main Menu");

        System.out.print("Choose Option: ");
        int option = input.nextInt();

        switch (option) {
            case 1 -> this.addCustomer(app, databaseName);
            case 2 -> this.restock();
            case 3 -> this.infoSeller();
            case 4 -> this.main(app);
            default -> {
                this.clearScreen();
                System.err.println("Unexpected Value: " + option + "choose the appropriate option");
                this.dataFileMenu(app, databaseName);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void addCustomer(App app, String databaseName) {
        this.clearScreen();
        JSONObject db = null;
        JSONObject selectedSeller = null;
        JSONObject sellersDb = null;
        String sellerName = null;

        System.out.println("Which package?");
        System.out.println("(1) Buy 1 product | 8k");
        System.out.println("(2) Buy 2 products | 15k");

        System.out.print("Choose Option: ");
        int option = input.nextInt();

        String dbFilePath = app.getDbFilePath();
        try (FileReader fileReader = new FileReader(dbFilePath)) {
            JSONParser jsonParser = new JSONParser();
            try {
                db = (JSONObject) jsonParser.parse(fileReader);
                sellersDb = (JSONObject) db.get("sellers");

                List<String> sellerKeys = new ArrayList<>(sellersDb.keySet());
                view.SellersListView(sellerKeys);
                
                System.out.print("Choose seller: ");
                int sellerIndex = input.nextInt() - 1;

                sellerName = sellerKeys.get(sellerIndex);

                selectedSeller = (JSONObject) sellersDb.get(sellerName);

                System.out.println(selectedSeller);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Input customer name: ");
        String customerName = input.next();

        int revenue = app.getRevenue();
        int product = app.getProductQuantity();

        switch (option) {
            case 1 -> {
                revenue += 8000;
                product -= 1;
            }
            case 2 -> {
                revenue += 15000;
                product -= 2;
            }
            default -> {
                System.err.println("Unexpected Value: " + option + "choose the appropriate option");
                this.addCustomer(app, databaseName);
            }
        }

        System.out.println("Customer: " + customerName);
        System.out.println("Package: " + option);
        
        if (this.getYesOrNo("Are you sure the data is correct?")) {
            switch (option) {
                case 1 -> {
                    JSONArray packageOne = (JSONArray) ((JSONObject) sellersDb.get(sellerName)).get("package-one");
                    packageOne.add(customerName);
                    ((JSONObject) sellersDb.get(sellerName)).put("package-one", packageOne);
                }
                case 2 -> {
                    JSONArray packageTwo = (JSONArray) ((JSONObject) sellersDb.get(sellerName)).get("package-two");
                    packageTwo.add(customerName);
                    ((JSONObject) sellersDb.get(sellerName)).put("package-two", packageTwo);
                }
            }

            try (FileWriter fileWriter = new FileWriter(dbFilePath)) {
                fileWriter.write(db.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.dataFileMenu(app, databaseName);
        } else {
            this.addCustomer(app, databaseName);
        }
    }

    public void restock() {

    }

    public void infoSeller() {

    }

    public boolean getYesOrNo(String validation) {
        // tampilkan validasi
        System.out.print(validation + " (y/n): ");
        String answer = input.next().toLowerCase();

        // jika input yang dimasukkan tidak sesuai aturan (y/n)
        if (!answer.equals("y") && !answer.equals("n")) {
            
            // tampilkan pesan error
            System.err.println("Unexpected Value: " + answer);
            System.out.println("Choose the apprpriate option (y/n)");

            // spasi kosong
            System.out.println();

            // tanya ulang
            return this.getYesOrNo(validation);
        }

        // spasi kosong
        System.out.println();
        return answer.equals("y");
    }

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033\143");
            }
        } catch (Exception e) {
            System.err.println("tidak bisa clear screen");
        }
    }

}
