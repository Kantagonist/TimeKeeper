package com.example.timekeeper

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.timekeeper.internal.FileWorker
import com.example.timekeeper.internal.TimeKeeperModel
import com.example.timekeeper.internal.TimePoint
import java.util.*

class MainActivity : AppCompatActivity() {

    var Model : TimeKeeperModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Model = TimeKeeperModel(this)
    }

    /**
     * Testing method.
     * Date from Java has the right miliseconds (i checked), but it interprets them wrong.
     * Not even kidding.
     */
    fun testCSVReader(){
        val d = Date()
        FileWorker.addTimePoint(TimePoint("testing_read_and_write", System.currentTimeMillis()))
        var timePoints = FileWorker.getListOfTimePointsSince(1)
        return
    }
}