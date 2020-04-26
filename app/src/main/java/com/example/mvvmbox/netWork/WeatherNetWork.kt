package com.example.mvvmbox.netWork

import com.example.mvvmbox.netWork.model.Location
import com.example.mvvmbox.netWork.model.LocationItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_UPL = "https://www.metaweather.com/api/location/"

interface WeatherNetWork {

    @GET("search?")
    fun getLocation( @Query("query")searchString : String) : Call<MutableList<LocationItem>>
}