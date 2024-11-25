package com.view.viewfunctions;

import java.util.List;

import org.json.simple.JSONObject;

import com.model.SellerModel;
import com.util.Util;

public class InfoSellerView {
    private InfoSellerView() {
        throw new IllegalStateException("Utility class");
    }

    public static void function(List<SellerModel> sellers) {
        for (SellerModel seller : sellers) {
            Util.println(seller.getName());

        }
    }
}
