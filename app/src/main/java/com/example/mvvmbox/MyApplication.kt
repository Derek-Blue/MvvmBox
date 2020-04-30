package com.example.mvvmbox

import android.app.Application
import com.example.mvvmbox.viewModel.DetailViewModel
import com.example.mvvmbox.viewModel.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    /**
     * 宣告一個 Application 提供 Koin 進行初始化
     * 要在　Ｍanifest　指定使用
     */

    private val viewModelModule = module {
        viewModel {
            DetailViewModel()
        }
    }

    private val viewModelModule2 = module {
        viewModel {
            SearchViewModel()
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(viewModelModule,viewModelModule2))
        }
    }
}