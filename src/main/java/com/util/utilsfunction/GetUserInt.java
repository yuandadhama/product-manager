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
            Util.print(str);
            try {
                // Read and return the integer input
                int input = scanner.nextInt();
                if (input <= 0) {
                    Util.println("Please enter a positive integer.");
                    Util.emptySpace();
                } else {
                    return input;
                }
            } catch (Exception e) {
                // Clear the invalid input from the buffer
                scanner.nextLine();
                Util.println("Please enter a valid integer.");
                Util.emptySpace();
            }
        }
    }
}
