package com.example.locationdemo

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MyListener:LocationListener{
    val lastlocation:Location?= Location(GPS_PROVIDER)
    var latitude:Double?=null
    var longtitude:Double?=null
    override fun onLocationChanged(location: Location?) {
       lastlocation?.set(location)
        latitude=lastlocation?.latitude
        longtitude=lastlocation?.longitude
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
}