package com.idealcn.lifecycle.study.converter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import com.idealcn.lifecycle.study.bean.BaseResponseBean;
import com.idealcn.lifecycle.study.bean.HomeArticleBean;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

public class LiveDataCallAdapter<T> implements CallAdapter<T,LiveData<T>> {
    private Type type;
    public LiveDataCallAdapter(Type type){
        this.type = type;
    }


    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public LiveData<T> adapt(Call<T> call) {
        return new LiveData<T>() {
            //保证操作的原子性
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            @Override
            protected void onActive() {
                super.onActive();
                if (atomicBoolean.compareAndSet(false,true)){
                    call.enqueue(new Callback<T>() {
                        @Override
                        public void onResponse(Call<T> call, Response<T> response) {
                            T body = response.body();
                            if (null!=body)
                                postValue(body);
                        }

                        @Override
                        public void onFailure(Call<T> call, Throwable t) {
                            try {
                                Response<T> response = call.execute();
                                postValue(null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        };
    }


}
