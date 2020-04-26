package com.example.mvvmbox.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmbox.netWork.BASE_UPL
import com.example.mvvmbox.netWork.WeatherNetWork
import com.example.mvvmbox.netWork.model.LocationItem
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRepository(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val locationList =  MutableLiveData<MutableList<LocationItem>>()

    fun changState(){
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun searchLocation(searchString : String){
        showProgress.value = true

        val retrofit = Retrofit.Builder().baseUrl(BASE_UPL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherNetWork::class.java)
        service.getLocation(searchString).enqueue(object : Callback<MutableList<LocationItem>>{
            override fun onFailure(call: Call<MutableList<LocationItem>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<MutableList<LocationItem>>,
                response: Response<MutableList<LocationItem>>
            ) {
                //Log.d("TAG_SEARCH", "Response : ${Gson().toJson(response.body())}")
                locationList.value = response.body()
                showProgress.value = false
            }

        })
    }
}