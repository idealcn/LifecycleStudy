package com.idealcn.lifecycle.study.converter;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.google.gson.reflect.TypeToken;
import com.idealcn.lifecycle.study.bean.BaseResponseBean;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @NonNull
    @Override
    public CallAdapter<?, ?> get(Type type, Annotation[] annotations, Retrofit retrofit) {
        /*
        getRawType: 返回对象类型
        getParameterUpperBound : 返回对象类型所包含的泛型类型
        比如:
                Type listType = new TypeToken<List<String>>() {}.getType();
                //对象类型
                Class<?> listClass = getRawType(listType);
                Type parameterType = getParameterUpperBound(0, (ParameterizedType) listType);
                //泛型类型
                Class<?> parameterClass = getRawType(parameterType);
         */
        if (getRawType(type) != LiveData.class){
            throw new RuntimeException("接口返回的对象类型不是LiveData");
        }
        Type parameterUpperBound = getParameterUpperBound(0, (ParameterizedType) type);
        Class<?> rawType = getRawType(parameterUpperBound);
        if (rawType!=BaseResponseBean.class){
            throw new RuntimeException("返回的不是BaseResponseBean");
        }

        if (!(parameterUpperBound instanceof ParameterizedType)){
            throw new RuntimeException("--------");
        }



        return new LiveDataCallAdapter<>(parameterUpperBound);
    }



    public static LiveDataCallAdapterFactory create(){
        return new  LiveDataCallAdapterFactory();
    }
}
