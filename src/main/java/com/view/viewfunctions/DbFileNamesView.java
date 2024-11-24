package com.view.viewfunctions;

import java.util.List;

import com.util.Util;

public class DbFileNamesView {
    private DbFileNamesView() {
        throw new IllegalStateException("Utility class");
    }
    public static void show(List<String> dataFileNames) {

        // title
        Util.clearScreen();
        Util.println("== Database List ==");
        Util.emptySpace();

        int order = 1;
        for (String fileName : dataFileNames) {
            // hilangkan extension file
            fileName = fileName.replaceAll(".json", "");

            // tampilkan nama-nama file data yang sudah diformat
            Util.println(String.format("(%d) %s", order, fileName));

            // sesuaikan urutan nama file yang akan ditampilkan selanjutnya
            order++;
        }
    }
}
