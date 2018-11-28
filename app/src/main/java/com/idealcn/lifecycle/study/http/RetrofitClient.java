package com.idealcn.lifecycle.study.http;

import com.idealcn.lifecycle.study.Api;
import com.idealcn.lifecycle.study.converter.LiveDataCallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final RetrofitClient INSTANCE = new RetrofitClient();


    public static RetrofitClient newInstance(){
        return INSTANCE;
    }

    private Retrofit retrofit;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .baseUrl("http://www.wanandroid.com/")
                .build();
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
