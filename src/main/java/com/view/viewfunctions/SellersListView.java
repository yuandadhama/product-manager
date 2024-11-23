package com.view.viewfunctions;

import java.util.Collections;
import java.util.List;
import com.util.Util;

public class SellersListView {
    private SellersListView() {
        throw new IllegalStateException("Utility class");
    }

    public static void show(List<String> sellerNames) {
        Util.emptySpace();
        Util.println("== Seller List ==");
        Collections.sort(sellerNames);

        int order = 1;
        for (String seller : sellerNames) {
            Util.println(String.format("(%d) %s", order, seller));
            order++;
        }
    }
}
