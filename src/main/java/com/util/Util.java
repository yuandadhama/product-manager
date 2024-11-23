package com.util;

import java.io.IOException;
import java.text.DecimalFormat;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.util.utilsfunction.*;

public class Util {
    private Util() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * get user input integer
     * 
     * @param message info/question validation
     * @return
     */
    public static int getUserInt(String validation) {
        return GetUserInt.function(validation);
    }

    /**
     * Gets a string input from the user.
     * 
     * @return the input string
     */
    public static String getUserString(String validation) {
        return GetUserString.function(validation);
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
     * Reads a JSON file and returns its content as a JSONObject.
     * 
     * @param filePath the path to the JSON file
     * @return the JSONObject representing the file's content
     */
    public static JSONObject readFileToJson(String filePath) throws IOException, ParseException {
        return ReadFileToJson.function(filePath);
    }

    /**
     * read some jsonObject in file of FilePath
     * 
     * @param jsonObject to be written
     * @param filePath   to store data of jsonObject
     */
    public static void writeJsonToFile(JSONObject jsonObject, String filePath) throws IOException {
        WriteJsonToFile.function(jsonObject, filePath);
    }

    /**
     * convert the input string into Title Case
     * 
     * @param input string
     * @return Title Case string
     */
    public static String toTitleCase(String input) {
        return ToTitleCase.function(input);
    }

    /**
     * Formats an integer by adding thousands separators (dots).
     * For example, an input of 1000000 will be formatted as "1.000.000".
     * <p>
     * This method uses {@link java.text.DecimalFormat} to format the number with
     * commas and then replaces
     * those commas with dots, ensuring the result is compatible with number
     * formatting standards that use
     * dots as thousands separators.
     * </p>
     *
     * @param number The integer value to be formatted.
     * @return A string representing the formatted number with dots as thousands
     *         separators.
     *         If the input number is less than 1000, it returns the number as is
     *         (without any separators).
     * @throws IllegalArgumentException If the input number is negative (this
     *                                  function assumes positive integers).
     *
     * @see java.text.DecimalFormat
     */
    public static String formatNumber(int number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number).replace(",", ".");
    }

    /**
     * clean all the written message in terminal
     */
    public static void clearScreen() {
        ClearScreen.function();
    }
}