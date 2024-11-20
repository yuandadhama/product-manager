package com.model;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SellerModel {
    private String name;
    private JSONArray packageOne = new JSONArray();
    private JSONArray packageTwo = new JSONArray();

    public SellerModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        HashMap<String, JSONArray> map = new HashMap<>();
        map.put("package-one", packageOne);
        map.put("package-two", packageTwo);
        return new JSONObject(map);
    }
}