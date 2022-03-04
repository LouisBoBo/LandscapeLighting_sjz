package com.exc.applibrary.main;


import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class HttpListener<T> {


    public abstract void onSuccess(T result);

    public abstract void onError();


    Type getType() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (type instanceof Class) {
            return type;
        } else {
            return new TypeToken<T>() {
            }.getType();

        }
    }
}
