package com.example.locationdemo.CustomRecycleView

import android.app.ProgressDialog.show
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.locationdemo.R
import java.io.Serializable

class CustomRecycleViewAdapter: RecyclerView.Adapter<CustomViewHolder> ,Serializable{
    var list:List<LocationDataModel> = emptyList()
    lateinit var context:Context

    constructor(list: List<LocationDataModel>, context: Context) : super() {
        this.list = list
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var context:Context=parent.context
        var inflator:LayoutInflater= LayoutInflater.from(context)
        var view:View=inflator.inflate(R.layout.recyclerview_layout,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.latview.setText(list[position].latitude)
        holder.longtview.setText(list[position].longtitude)
        holder.timeview.setText(list[position].time)
        holder.layout.setOnClickListener {
            v: View? ->
            Toast.makeText(v?.context,list[position].time.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}