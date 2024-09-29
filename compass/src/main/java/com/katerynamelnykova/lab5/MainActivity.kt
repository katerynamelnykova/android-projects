package com.katerynamelnykova.lab5

import androidx.appcompat.app.AppCompatActivity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var image: ImageView
    private lateinit var tvHeading: TextView
    private var currentDegree = 0f
    private var mSensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image = findViewById(R.id.compass)
        tvHeading = findViewById(R.id.tvHeading)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(
            this,
            mSensorManager!!.getDefaultSensor(
                Sensor.TYPE_ORIENTATION
            ), SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree = Math.round(event!!.values[0]).toFloat()
        tvHeading.text = "${printResult(degree)}"
        val ra = RotateAnimation(
            currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        ra.duration = 200
        ra.fillAfter = true
        image.startAnimation(ra)
        currentDegree = -degree
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

}

fun printResult(degrees: Float) : String {
    var result = ""
    if(degrees == (0).toFloat()) {
        result = "0° N"
    }
    if(degrees == (90.0).toFloat()) {
        result = "90° E"
    }
    if(degrees == (180.0).toFloat()) {
        result = "180° S"
    }
    if(degrees == (270.0).toFloat()) {
        result = "270° W"
    }
    if(degrees > 0 && degrees < 90) {
        result = "${degrees}° NE"
    }
    if(degrees > 90 && degrees < 180) {
        result = "${degrees}° SE"
    }
    if(degrees > 180 && degrees < 270) {
        result = "${degrees}° SW"
    }
    if(degrees > 270 && degrees < 360) {
        result = "${degrees}° NW"
    }
    return result
}
