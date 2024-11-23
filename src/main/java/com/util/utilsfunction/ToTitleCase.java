package com.util.utilsfunction;

public class ToTitleCase {
     private ToTitleCase() {
        throw new IllegalStateException("Utility class");
    }

    public static String function(String input) {

        if (input == null || input.isEmpty()) {
            return input; // Handle null or empty input gracefully
        }
    
        String[] words = input.toLowerCase().split("\\s+"); // Split by spaces
        StringBuilder titleCase = new StringBuilder();
    
        for (String word : words) {
            if (!word.isEmpty()) {
                titleCase.append(Character.toUpperCase(word.charAt(0))) // Capitalize first letter
                         .append(word.substring(1)) // Append the rest of the word
                         .append(" "); // Add a space after each word
            }
        }
    
        return titleCase.toString().trim(); // Remove trailing space
    }
}
