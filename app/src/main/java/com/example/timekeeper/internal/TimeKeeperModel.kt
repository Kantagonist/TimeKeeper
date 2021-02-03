package com.example.timekeeper.internal

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import com.example.timekeeper.MainActivity

/**
 * A singleton, which holds the variables of the program.
 */
class TimeKeeperModel(activity: MainActivity){
    var TimePoints : List<TimePoint>
    var Clocks : List<Long>
    val AppMainActivity : MainActivity
    val AppContext : Context
    var color = Color.GREEN
    var newestTimeSlot = 0

    init {
        AppMainActivity = activity
        TimePoints = mutableListOf<TimePoint>()
        Clocks = mutableListOf<Long>()
        AppContext = AppMainActivity.applicationContext
        FileWorker.setUpInstance(this)
    }
}
