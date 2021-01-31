package com.example.timekeeper.internal

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.File
import java.lang.Exception
import java.time.LocalDateTime
import java.util.Date

/**
 * A data model of a recorded point in time.
 */
class TimePoint (id : String, timeInMiliseconds : Long){

    val TimeInMiliseconds : Long
    val Id : String
    val ActualDate : Date


    init {
        if(id.contains('.'))throw Exception("Id of Timepoint can not hold commas.")
        TimeInMiliseconds = timeInMiliseconds
        Id = id
        ActualDate = Date(TimeInMiliseconds)
    }

    fun toString() : String{
        return "TimePoint: [ $Id, $TimeInMiliseconds ] : $ActualDate"
    }
}