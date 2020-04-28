package com.example.locationdemo.CustomRecycleView

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_layout.view.*
import org.w3c.dom.Text

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var timeview:TextView
    lateinit var latview:TextView
    lateinit var longtview:TextView
    lateinit var  layout:RelativeLayout
    init {
        timeview=itemView.timetext as TextView
        latview=itemView.tvlatitude as TextView
        longtview=itemView.tvlongtitude as TextView
        layout=itemView.custom_root_layout as RelativeLayout
    }

}