package com.example.mvvmbox.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.mvvmbox.netWork.items.LocationItem
import com.example.mvvmbox.repository.ModelRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ViewModel() {

    private val repository = ModelRepository()

    private val _showProgress = MutableLiveData<Boolean>()  // VM 內部的 result
    val showProgress: LiveData<Boolean> = _showProgress   //提供View 監視(Observer) 的接口

    private val _locationList = MutableLiveData<List<LocationItem>>()  // VM 內部的 result
    val locationList: LiveData<List<LocationItem>> = _locationList  //提供View 監視(Observer) 的接口

    fun searchLocation(searchString: String) {

        viewModelScope.launch {

            _showProgress.value = true
            val service = repository.getAPI()

            withContext(ioDispatcher) {
                service.getLocation(searchString)
                    .enqueue(object : Callback<MutableList<LocationItem>> {
                        override fun onFailure(
                            call: Call<MutableList<LocationItem>>,
                            t: Throwable
                        ) {
                            _showProgress.value = false
                            t.printStackTrace()
                        }

                        override fun onResponse(
                            call: Call<MutableList<LocationItem>>,
                            response: Response<MutableList<LocationItem>>
                        ) {
                            Log.d("TAG", Gson().toJson(response.body()))
                            _locationList.value = response.body()
                            _showProgress.value = false
                        }
                    })
            }
        }
    }
}