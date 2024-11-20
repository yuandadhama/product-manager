package com.utils;

import java.util.Scanner;

public class Util {
    private static Scanner input = new Scanner(System.in);
    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static int getUserInt() {
        return input.nextInt();
    }

    public static String getUserString() {
        return input.next();
    }

    public static boolean getYesOrNo(String validation) {
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
            return Util.getYesOrNo(validation);
        }

        // spasi kosong
        System.out.println();
        return answer.equals("y");
    }
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033\143");
            }
        } catch (Exception e) {
            System.err.println("Cannot clear the screen");
        }
    }
}