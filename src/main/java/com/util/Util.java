package com.util;

import com.util.utilsfunction.ClearScreen;
import com.util.utilsfunction.GetUserInt;
import com.util.utilsfunction.GetUserString;
import com.util.utilsfunction.GetYesOrNo;

public class Util {
    private Util() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Gets an int input from the user.
     * 
     * @return the input int
     */
    public static int getUserInt(String str) {
        return GetUserInt.function(str);
    }

    /**
     * Gets a string input from the user.
     * 
     * @return the input string
     */
    public static String getUserString(String str) {
        return GetUserString.function(str);
    }

    /**
     * ask user the selection
     * 
     * @return (y = true) (n = false) 
     */
    public static boolean getYesOrNo(String validation) {
        return GetYesOrNo.function(validation);
    }

    /**
     * display message using System.out.print()
     */
    public static void print(String str) {
        System.out.print(str);
    }

    /**
     * display message using System.out.println()
     */
    public static void println(String str) {
        System.out.println(str);
    }

    /**
     * new line output separator
     */
    public static void emptySpace() {
        System.out.println();
    }

    /**
     * clean all the written message in terminal
     */
    public static void clearScreen() {
        ClearScreen.function();
    }
}