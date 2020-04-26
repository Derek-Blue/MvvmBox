package com.example.mvvmbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mvvmbox.repository.DetailRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    val repository = DetailRepository(application)
}