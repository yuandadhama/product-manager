package com;

import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);
    public void main(App app) {
        System.out.println("Main Menu:");
        System.out.println("(1) Create New Field");
        System.out.println("(2) Update Existed Field");

        System.out.print("Choose Option: ");
        int option = input.nextInt();

        switch (option) {
            case 1 -> app.createField();
            case 2 -> {
                boolean isFromNewFile = false;
                app.showsField(isFromNewFile, null);
            }
            default -> System.err.println("Unexpected Value: " + option + "choose the appropriate option");
        }

    }

    public void dataFileMenu (App app, String databaseName) {
        System.out.println(databaseName);
        System.out.println("Capital: " + app.getCapital());
        System.out.println("Product: " + app.getProduct());
        System.out.println("Revenue: " + app.getRevenue());
        System.out.println("Profit: " + app.getProfit());

        
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
