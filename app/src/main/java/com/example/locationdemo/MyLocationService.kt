package com.example.locationdemo

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.locationdemo.CustomRecycleView.CustomRecycleViewAdapter
import com.example.locationdemo.CustomRecycleView.DataUpdater
import com.example.locationdemo.CustomRecycleView.LocationDataModel
import java.io.Serializable
import java.math.BigInteger

class MyLocationService : Service() {
    var locationmanager: LocationManager? = null
    lateinit var locationlistener:MyListener
    var binder:LocalBinder ?=null
    lateinit var dataUpdater:MyListener
    var list:ArrayList<LocationDataModel> ?= null
    var Adapter:CustomRecycleViewAdapter ?= null
    companion object{
        val INTERVAL=1000.toLong()
        val DISTANCE=1.toFloat()
    }
    init {
        binder=LocalBinder()
        locationlistener=MyListener()
    }

    override fun onBind(intent: Intent?): IBinder? {

        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        Toast.makeText(applicationContext,"Service Start", Toast.LENGTH_LONG).show()

        try {
            locationmanager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, INTERVAL, DISTANCE,locationlistener)
        } catch (e: Exception) {
            Log.i("Service Exception",e.message)
        }
        return START_STICKY
    }

    @SuppressLint("ServiceCast")
    override fun onCreate() {
        super.onCreate()
        Log.i("MyService","Service Created")
        locationmanager=applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext,"Service destroy", Toast.LENGTH_LONG).show()
        Log.i("MyService","Service Destroyed")
    }

    inner class LocalBinder():Binder(){
        fun getService():MyLocationService=this@MyLocationService
    }
}




