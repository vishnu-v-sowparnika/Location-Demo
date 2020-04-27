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
