package com.controller.infoseller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.model.DatabaseModel;
import com.model.SellerModel;
import com.util.Util;
import com.view.AppView;

public class InfoSeller {
    private InfoSeller() {
        throw new IllegalStateException("Utility Class");
    }

    public static void function(DatabaseModel databaseModel, AppView view) {
        JSONObject sellers = databaseModel.getSellers();
        
        view.infoSellerView(sellers);
    }
}
