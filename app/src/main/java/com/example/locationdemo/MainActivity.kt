package com.example.locationdemo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.locationdemo.CustomRecycleView.CustomRecycleViewAdapter
import com.example.locationdemo.CustomRecycleView.DataUpdater
import com.example.locationdemo.CustomRecycleView.LocationDataModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() ,Serializable{
     private   val location_requestcode=200
    private var location_list:ArrayList<LocationDataModel> = ArrayList()
   lateinit var MyAdapter:CustomRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
        var customrecyclerview:RecyclerView=recyclerView
        customrecyclerview.setHasFixedSize(true)
        customrecyclerview.layoutManager=LinearLayoutManager(this)
        MyAdapter= CustomRecycleViewAdapter(location_list,application)
        customrecyclerview.adapter=MyAdapter
        DataUpdater.adapter=MyAdapter
        DataUpdater.list=location_list

       startbutton.setOnClickListener {
            val intent=Intent(this,MyLocationService::class.java)

           try {
              // intent.putExtra("bind_object",dataupdater as Serializable)
               startService(intent)
           } catch (e: Exception) {
               Log.i("Exception service",e.message)
           }
       }
        stopbutton.setOnClickListener {
            //Toast.makeText(applicationContext,"Hello",Toast.LENGTH_LONG).show()
            val intent=Intent(this,MyLocationService::class.java)
            stopService(intent)
        }


//        var item1 :LocationDataModel = LocationDataModel("10:30","201.33","40.3320")
//        var item2 :LocationDataModel = LocationDataModel("9:30","101.33","140.3320")
//
//        var item3 :LocationDataModel = LocationDataModel("8:30","201.33","40.3320")
//        var item4 :LocationDataModel = LocationDataModel("6:30","101.33","140.3320")
//        var item5 :LocationDataModel = LocationDataModel("5:30","101.33","140.3320")
//
//        location_list.add(item1)
//        location_list.add(item2)
//
//        location_list.add(item3)
//        location_list.add(item4)
//        location_list.add(item5)



    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.isNotEmpty()){
            if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext,"Please allow permission",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkPermission() {

            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                //ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),location_requestcode)
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),location_requestcode)
                }

            }
    }




}
