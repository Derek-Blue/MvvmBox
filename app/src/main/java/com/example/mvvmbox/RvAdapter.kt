package com.example.mvvmbox

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmbox.netWork.model.LocationItem
import com.example.mvvmbox.view.DetailActivity
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var list : MutableList<LocationItem> = mutableListOf()

    fun addlist(list: MutableList<LocationItem>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.locationNameTV!!
        val lating = view.latLngTV!!
        val rootView = view.itemRoot!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reItem = list[position]
        holder.name.text = reItem.title
        holder.lating.text = reItem.latt_long
        holder.rootView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }
}