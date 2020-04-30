package com.example.mvvmbox.netWork

import com.example.mvvmbox.netWork.items.LocationItem
import com.example.mvvmbox.netWork.items.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_UPL = "https://www.metaweather.com/api/location/"

interface WeatherNetWork {

    @GET("search?")
    fun getLocation( @Query("query")searchString : String) : Call<MutableList<LocationItem>>

    @GET("{woeid}")
    fun getWeather (@Path("woeid") woeid : String) : Call<WeatherResponse>
}