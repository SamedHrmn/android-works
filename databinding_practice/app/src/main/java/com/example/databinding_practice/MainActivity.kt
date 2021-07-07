package com.example.databinding_practice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.databinding_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        changeText()

    }

    private fun changeText(){
        activityMainBinding.activityTextView.text = "Changed by ViewBinding"
    }


}