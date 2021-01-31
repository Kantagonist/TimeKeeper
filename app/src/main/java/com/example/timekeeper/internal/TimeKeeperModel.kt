package com.example.timekeeper.internal

import androidx.appcompat.app.AppCompatActivity
import com.example.timekeeper.MainActivity

/**
 * A singleton, which holds the variables of the program.
 */
class TimeKeeperModel(activity: MainActivity){
    var TimePoints : List<TimePoint>
    val AppMainActivity : MainActivity

    init {
        AppMainActivity = activity
        TimePoints = mutableListOf<TimePoint>()
    }
}
