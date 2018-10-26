package com.idealcn.lifecycle.study.converter;

import android.arch.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class LiveDataConverterFactory extends Converter.Factory {

    private Gson gson = new Gson();

    private LiveDataConverterFactory(){}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new  LiveDataConverter<>(adapter,gson);
    }

    @NotNull
    public static Converter.Factory create() {
        return new LiveDataConverterFactory();
    }
}
