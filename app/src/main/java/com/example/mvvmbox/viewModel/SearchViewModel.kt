package com.example.mvvmbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvmbox.netWork.model.LocationItem
import com.example.mvvmbox.repository.SearchRepository

class SearchViewModel(application: Application)  : AndroidViewModel(application){

    private val repository = SearchRepository(application)
    val showProgress : LiveData<Boolean>
    val locationList : LiveData<MutableList<LocationItem>>

    init {
        this.showProgress = repository.showProgress
        locationList = repository.locationList
    }

    fun changState(){
        repository.changState()
    }

    fun searchLocation(searchString : String){
        repository.searchLocation(searchString)
    }
}