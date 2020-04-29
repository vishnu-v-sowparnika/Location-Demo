package com.example.locationdemo

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import com.example.locationdemo.CustomRecycleView.CustomRecycleViewAdapter
import com.example.locationdemo.CustomRecycleView.DataUpdater
import com.example.locationdemo.CustomRecycleView.LocationDataModel
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyListener():LocationListener{
    val lastlocation:Location?= Location(GPS_PROVIDER)
    var latitude:Double?=null
    var longtitude:Double?=null

    override fun onLocationChanged(location: Location?) {
       lastlocation?.set(location)
        var list=DataUpdater.list
        var adapter=DataUpdater.adapter
        latitude=lastlocation?.latitude
        longtitude=lastlocation?.longitude
        var time:String=getFormattedDate()
        var item:LocationDataModel= LocationDataModel(time,latitude = latitude.toString(),longtitude = longtitude.toString())
        list.add(item)
        adapter.notifyDataSetChanged()
        Log.i("Values","Latitude $latitude, Longtitude $longtitude");
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun getFormattedDate():String{
        var sdf:SimpleDateFormat= SimpleDateFormat("yyyy:MM:dd  hh:mm:ss a")
        var stringDate:String= sdf.format(Date())
        return stringDate
    }
}