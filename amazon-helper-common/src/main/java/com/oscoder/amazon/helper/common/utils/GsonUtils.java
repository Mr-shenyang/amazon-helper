package com.oscoder.amazon.helper.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonUtils {
    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static String toJson(Object src){
        return GSON.toJson(src);
    }

    public static <T>  T fromJson(String json,Class<T> clz){
        return GSON.fromJson(json,clz);
    }

    public <T> T fromJson(String json, Type typeOfT){
        return GSON.fromJson(json,typeOfT);
    }
}
