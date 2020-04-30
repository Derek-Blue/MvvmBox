package com.example.mvvmbox.netWork.items

class WeatherResponse (
    val consolidated_weather : List<ConsolidatedWeather>,
    val time : String,
    val title : String,
    val woeid : Int
)