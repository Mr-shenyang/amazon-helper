package com.oscoder.amazon.helper.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    public static final Gson GSON = new GsonBuilder().create();

    public static String toJson(Object src){
        return GSON.toJson(src);
    }
}
