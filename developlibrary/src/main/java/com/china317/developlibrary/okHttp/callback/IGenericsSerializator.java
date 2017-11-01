package com.china317.developlibrary.okHttp.callback;


public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
