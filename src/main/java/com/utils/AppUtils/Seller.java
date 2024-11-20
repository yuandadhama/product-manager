package com.utils.AppUtils;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Seller {
    private String name;
    private JSONArray packageOne = new JSONArray();
    private JSONArray packageTwo = new JSONArray();

    public Seller(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        HashMap<String, JSONArray> map = new HashMap<String, JSONArray>();
        map.put("package-one", packageOne);
        map.put("package-two", packageTwo);
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }
}
