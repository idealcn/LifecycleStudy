package com.idealcn.lifecycle.study.converter;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

import java.lang.reflect.Type;

public class LiveDataCallAdadpter<T> implements CallAdapter<Response,T> {
    @Override
    public Type responseType() {
        return null;
    }

    @Override
    public T adapt(Call<Response> call) {

        return null;
    }
}
