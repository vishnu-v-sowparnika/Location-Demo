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
import com.example.locationdemo.CustomRecycleView.LocationDataModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
        val location_requestcode=200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
       startbutton.setOnClickListener {
            val intent=Intent(this,MyLocationService::class.java)
           startService(intent);
       }
        stopbutton.setOnClickListener {
            //Toast.makeText(applicationContext,"Hello",Toast.LENGTH_LONG).show()
            val intent=Intent(this,MyLocationService::class.java)
            stopService(intent)
        }

        var location_list:ArrayList<LocationDataModel> = ArrayList()
        var item1 :LocationDataModel = LocationDataModel("10:30","201.33","40.3320")
        var item2 :LocationDataModel = LocationDataModel("9:30","101.33","140.3320")

        location_list.add(item1)
        location_list.add(item2)

        var customrecyclerview:RecyclerView=recyclerView
        customrecyclerview.setHasFixedSize(true)
        customrecyclerview.layoutManager=LinearLayoutManager(this)
        var MyAdapter:CustomRecycleViewAdapter= CustomRecycleViewAdapter(location_list,application)
        customrecyclerview.adapter=MyAdapter
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
