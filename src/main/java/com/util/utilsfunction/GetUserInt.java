package com.util.utilsfunction;

import java.util.Scanner;

import com.util.Util;

public class GetUserInt {
    private static Scanner scanner = new Scanner(System.in);

    private GetUserInt() {
        throw new IllegalStateException("Utility class");
    }

    public static int function(String str) {
        while (true) {
            // Read the input as a string
            Util.print(str);
            String input = scanner.nextLine().trim();
            try {
                // Read and return the integer input
                int number = Integer.parseInt(input);

                if (number > 0) {
                    return number; // Return valid input
                } else {
                    Util.println("Please enter a positive integer.");
                    Util.emptySpace();
                }
            } catch (Exception e) {
                Util.println("Please enter a valid integer.");
                Util.emptySpace();
            }
        }
    }
}
