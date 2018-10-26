package com.idealcn.lifecycle.study.converter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import okhttp3.ResponseBody
import retrofit2.Converter

import java.io.IOException

class LiveDataConverter<T>(private val adapter: TypeAdapter<T>, private val gson: Gson) :
    Converter<ResponseBody, LiveData<T>> {
    private val liveData = MutableLiveData<T>()

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): LiveData<T> {
        val reader = gson.newJsonReader(value.charStream())
        val result = adapter.read(reader)
        liveData.value = result
        return liveData
    }
}
