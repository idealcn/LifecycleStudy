package com.idealcn.lifecycle.study.converter;

import android.support.annotation.NonNull;
import com.google.gson.reflect.TypeToken;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @NonNull
    @Override
    public CallAdapter<?, ?> get(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeToken<?> typeToken = TypeToken.get(type);
        Class<?> clazz = getRawType(type);

        return new LiveDataCallAdadpter<>();
    }
}
