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
            case 2 -> app.updateField();
            default -> System.err.println("Unexpected Value: " + option + "choose the appropriate option");
        }

    }

    public boolean isYes(String validation) {
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
