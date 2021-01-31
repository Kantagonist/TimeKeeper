package com.example.timekeeper.internal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.timekeeper.MainActivity

/**
 * A singleton, which holds the variables of the program.
 */
class TimeKeeperModel(activity: MainActivity){
    var TimePoints : List<TimePoint>
    val AppMainActivity : MainActivity
    val AppContext : Context

    init {
        AppMainActivity = activity
        TimePoints = mutableListOf<TimePoint>()
        AppContext = AppMainActivity.applicationContext
        FileWorker.setUpInstance(this)
    }
}
