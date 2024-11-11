package com;

import java.util.Scanner;

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
            case 1 -> app.createField();
            case 2 -> app.showsField();
            default -> {
                System.err.println("Unexpected Value: " + option + "choose the appropriate option");
                this.main(app);
            }
        }

    }

    public void dataFileMenu (App app, String databaseName) {
        this.clearScreen();
        System.out.println("========== " + databaseName + " ==========");
        System.out.println("Capital: " + app.getCapital());
        System.out.println("Product: " + app.getProduct());
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
            case 1 -> this.addCustomer(app);
            case 2 -> this.restock();
            case 3 -> this.infoSeller();
            default -> {
                this.clearScreen();
                System.err.println("Unexpected Value: " + option + "choose the appropriate option");
                this.dataFileMenu(app, databaseName);
            }
        }
    }

    public void addCustomer(App app) {
        this.clearScreen();

        System.out.println("Which package?");
        System.out.println("(1) Buy 1 product | 8k");
        System.out.println("(2) Buy 2 products | 15k");
        
        System.out.print("Choose Option: ");
        int option = input.nextInt();

        System.out.print("Input customer name: ");
        String customerName = input.next();

        int revenue = app.getRevenue();
        int product = app.getProduct();

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
                this.addCustomer(app);
            }
        }

        System.out.println("Customer: " + customerName);
        System.out.println("Package: " + option);


        if (this.getYesOrNo("Are you sure the data is correct?")) {
            app.setRevenue(revenue);
            app.setProduct(product);
        } else {
            
        }
    }

    public void restock() {
    
    }

    public void infoSeller() {
    
    }


    public boolean getYesOrNo(String validation) {
        System.out.print(validation + " (y/n): ");
        String answer = input.next().toLowerCase();
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
