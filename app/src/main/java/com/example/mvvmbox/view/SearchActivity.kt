package com.example.mvvmbox.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.mvvmbox.R
import com.example.mvvmbox.RvAdapter
import com.example.mvvmbox.viewModel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModel() //Koin 提供的初始化(省略注入參數)
    private lateinit var adapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchImageV.setOnClickListener {
            if(searchEdit.text!!.isNotEmpty()) {
                viewModel.searchLocation(searchEdit.text.toString())
            }
        }

        viewModel.showProgress.observe(this, Observer {
            if (it){
                searchProgress.visibility = View.VISIBLE
            }else{
                searchProgress.visibility = View.GONE
            }
        })

        viewModel.locationList.observe(this, Observer {
            adapter.addList(it)
        })

        adapter = RvAdapter()
        searchRcView.adapter = adapter
    }
}
