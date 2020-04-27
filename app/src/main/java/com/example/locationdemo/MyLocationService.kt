package com.example.locationdemo

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.math.BigInteger

class MyLocationService : Service() {
    var locationmanager: LocationManager? = null
    var locationlistner : MyListener?=null
    companion object{
        val INTERVAL=10000.toLong()
        val DISTANCE=1.toFloat()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        Toast.makeText(applicationContext,"Service Start", Toast.LENGTH_LONG).show()

        try {
            locationmanager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, INTERVAL, DISTANCE,locationlistner)
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
        locationlistner= MyListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext,"Service destroy", Toast.LENGTH_LONG).show()
        Log.i("MyService","Service Destroyed")
    }


}




