package com.example.mvvmbox.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmbox.R
import com.example.mvvmbox.RvAdapter
import com.example.mvvmbox.viewModel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

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
            adapter.addlist(it)
        })

        adapter = RvAdapter()
        searchRcView.adapter = adapter
    }
}
