package com.example.timekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timekeeper.internal.TimeKeeperModel

class MainActivity : AppCompatActivity() {

    var Model : TimeKeeperModel = TimeKeeperModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}