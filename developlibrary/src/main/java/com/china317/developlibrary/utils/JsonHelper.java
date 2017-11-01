package com.china317.developlibrary.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/1/12.
 */
public class JsonHelper {
    final static boolean DEBUG = false;

    private static JsonHelper mSelf;

    public static JsonHelper getInstance() {
        return Factory.DEFAULT_HELPER;
    }


    private Gson mGson;

    private JsonHelper() {

        GsonBuilder builder = new GsonBuilder();

        if (DEBUG) {
            builder.setPrettyPrinting();
        }

        builder.addDeserializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getAnnotation(Ignore.class) != null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz.getAnnotation(Ignore.class) != null;
            }
        });

        mGson = builder.create();

    }


    public <T> T fromJson(String json, Class<T> tClass) {
        return mGson.fromJson(json, tClass);
    }


    public <T> T fromJson(String json, Type type) {
        return mGson.fromJson(json, type);
    }


    public String toJson(Object o) {
        return mGson.toJson(o);
    }


    static class Factory {
        public final static JsonHelper DEFAULT_HELPER = new JsonHelper();
    }
}
