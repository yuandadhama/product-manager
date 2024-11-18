package com.util;
import java.util.Scanner;
import java.util.logging.Logger;
import com.App;
import com.Menu;

public class CreateNewDBUtils {

    Logger log = Logger.getLogger(getClass().getName());
    Scanner input = new Scanner(System.in);
    public void setData(App app) {
        System.out.println("== Creating New DB ==");

        System.out.print("Input capital: ");
        app.setCapital(input.nextInt());

        System.out.print("Input Total Product: ");
        app.setCapital(input.nextInt());

        System.out.print("Input how many sellers: ");
        app.setCapital(input.nextInt());
    }

    public void showData(App app) {
        System.out.println("capital: " + app.getCapital());
        System.out.println("Products: " + app.getProduct());
        System.out.println("Total Seller: " + app.getTotalSellers() + " (It can be added later)");
    }
}
