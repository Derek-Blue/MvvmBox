package com.example.mvvmbox.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mvvmbox.R
import com.example.mvvmbox.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModel()

    companion object{
        private const val LOCATION_KEY = "location"
        private const val NAME_KEY = "name"

        /**
         * 定義一個 intent 至此的靜態方法  方便控管 bundle KEY 值
         */
        fun newIntent(woeid: String,
                      title: String,
                      context: Context) : Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(LOCATION_KEY, woeid)
            intent.putExtra(NAME_KEY, title)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val location = intent?.getStringExtra(LOCATION_KEY) ?: ""
        val title = intent?.getStringExtra(NAME_KEY) ?: ""

        locationText.text = title
        viewModel.getWeather(location)

        viewModel.showProgress.observe(this, Observer {
            if (it){
                detailsProgress.visibility = View.VISIBLE
            }else{
                detailsProgress.visibility = View.GONE
            }
        })

        viewModel.weatherResponse.observe(this, Observer {
            if (it != null){
                tempText.text = it.consolidated_weather[0].the_temp.toString()
            }
        })

    }
}
