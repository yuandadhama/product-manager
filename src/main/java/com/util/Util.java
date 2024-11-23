package com.util;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.util.utilsfunction.*;

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
     * read a file and convert the data into JSONObject from filePath (str)
     * 
     * @param filePath to be Read
     * @return JSONObject
     */
    public static JSONObject readFileToJson(String filePath) throws IOException, ParseException {
        return ReadFileToJson.function(filePath);
    }

    /**
     * read some jsonObject in file of FilePath
     * 
     * @param jsonObject to be written
     * @param filePath to store data of jsonObject
     */
    public static void writeJsonToFile(JSONObject jsonObject, String filePath) throws IOException {
        WriteJsonToFile.function(jsonObject, filePath);
    }
    /**
     * clean all the written message in terminal
     */
    public static void clearScreen() {
        ClearScreen.function();
    }
}