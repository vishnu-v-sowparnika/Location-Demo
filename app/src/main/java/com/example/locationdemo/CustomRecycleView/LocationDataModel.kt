package com.example.locationdemo.CustomRecycleView

class LocationDataModel {
    lateinit var time:String
    lateinit var latitude:String
    lateinit var longtitude:String

    constructor(time: String, latitude: String, longtitude: String) {
        this.time = time
        this.latitude = latitude
        this.longtitude = longtitude
    }
}