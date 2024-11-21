package com.util.utilsfunction;

import com.util.Util;

public class GetYesOrNo {
    private GetYesOrNo() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean function(String validation) {
        while (true) {
            // tampilkan validasi
            String answer = Util.getUserString(validation + " (y/n): ").toLowerCase();
            Util.emptySpace();

            if (answer.equals("y")) {
                return true;
            } else if (answer.equals("n")) {
                return false;
            } else {
                // jika input yang dimasukkan tidak sesuai aturan (y/n)
                Util.println("Choose the appropriate option (y/n)");
                Util.emptySpace();
            }
        }
    }

}
