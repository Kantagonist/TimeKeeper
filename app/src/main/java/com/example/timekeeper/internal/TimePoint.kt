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
class TimePoint (id : String, timeInMiliseconds : Long, lengthInMiliseconds : Long){

    val TimeInMiliseconds : Long //The point in time when this Point was created
    val Id : String //its id
    val ActualDate : Date //the translated date from the given miliseconds timestamp
    val LengthInMiliseconds : Long //how long the clocks were active


    init {
        if(id.contains('.'))throw Exception("Id of Timepoint can not hold commas.")
        TimeInMiliseconds = timeInMiliseconds
        Id = id
        ActualDate = Date(TimeInMiliseconds)
        LengthInMiliseconds = lengthInMiliseconds
    }

    override fun toString() : String{
        return "TimePoint: [ $Id, $TimeInMiliseconds, length: $LengthInMiliseconds ] : $ActualDate"
    }
}