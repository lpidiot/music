package com.idiot.music.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class IdiotUtils {
    public static HashMap<String, Object> jsonToHaMapObj(JSONObject jsonResource) {
        HashMap<String, Object> map = new LinkedHashMap<>();
        if(jsonResource==null){
            return null;
        }
        for (Map.Entry<String, Object> entry : jsonResource.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static HashMap<String, String> jsonToHaMap(JSONObject jsonResource) {
        HashMap<String, String> map = new LinkedHashMap<>();
        if(jsonResource==null){
            return null;
        }
        for (Map.Entry<String, Object> entry : jsonResource.entrySet()) {
            map.put(entry.getKey(),entry.getValue().toString());
        }
        return map;
    }

    public static void updateCookie(String cookies){
        String[] split = cookies.split(";");
    }
}
