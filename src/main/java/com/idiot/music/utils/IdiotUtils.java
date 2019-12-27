package com.idiot.music.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IdiotUtils {
    public static HashMap<String, Object> jsonToHaMapObj(JSONObject jsonResource) {
        HashMap<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : jsonResource.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static HashMap<String, String> jsonToHaMap(JSONObject jsonResource) {
        HashMap<String, String> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : jsonResource.entrySet()) {
            map.put(entry.getKey(),entry.getValue().toString());
        }
        return map;
    }
}
