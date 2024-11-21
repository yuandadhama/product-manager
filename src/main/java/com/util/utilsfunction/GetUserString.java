package com.util.utilsfunction;

import java.util.Scanner;

import com.util.Util;

public class GetUserString {
    private static Scanner scanner = new Scanner(System.in);

    private GetUserString() {
        throw new IllegalStateException("Utility class");
    }

    public static String function(String str) {
        String input;
        while (true) {
            Util.print(str);
            input = scanner.nextLine().trim(); // Read and trim input
            if (!input.isEmpty() && input.matches("[a-zA-Z\\s]+")) {
                return input; // Return valid string
            } else {
                Util.println("Please input a valid string, numbers and symbols are not allowed");
                Util.emptySpace();
            }
        }
    }
}
