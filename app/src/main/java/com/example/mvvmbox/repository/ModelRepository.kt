package com.example.mvvmbox.repository

import com.example.mvvmbox.netWork.BASE_UPL
import com.example.mvvmbox.netWork.WeatherNetWork
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModelRepository {

     fun getAPI (): WeatherNetWork{
        val retrofit = Retrofit.Builder().baseUrl(BASE_UPL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherNetWork::class.java)
    }
}