package com.example.mvvmbox.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmbox.netWork.items.WeatherResponse
import com.example.mvvmbox.repository.ModelRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class DetailViewModel(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val repository = ModelRepository()

    private val _showProgress = MutableLiveData<Boolean>()  // VM 內部的 result
    val showProgress: LiveData<Boolean> = _showProgress   //提供View 監視(Observer) 的接口

    private val _weatherResponse = MutableLiveData<WeatherResponse>()  // VM 內部的 result
    val weatherResponse: LiveData<WeatherResponse> = _weatherResponse  //提供View 監視(Observer) 的接口

    fun getWeather(woeid: String) {
        viewModelScope.launch {
            _showProgress.value = true
            val service = repository.getAPI()
            withContext(ioDispatcher) {
                service.getWeather(woeid).enqueue(object : retrofit2.Callback<WeatherResponse> {
                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        _showProgress.value = false
                        t.printStackTrace()
                    }
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        Log.d("TAG", Gson().toJson(response.body()))
                        _weatherResponse.value = response.body()
                        _showProgress.value = false
                    }

                })
            }
        }
    }

}