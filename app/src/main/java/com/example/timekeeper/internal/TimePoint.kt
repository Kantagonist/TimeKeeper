package com.example.timekeeper.internal

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.File
import java.time.LocalDateTime
import java.util.Date

/**
 * A data model of a recorded point in time.
 */
class TimePoint (timeInMiliseconds : Long, id : String){

    val TimeInMiliseconds : Long
    val Id : String
    val ActualDate : Date


    init {

        TimeInMiliseconds = timeInMiliseconds
        Id = id
        ActualDate = Date(TimeInMiliseconds)
    }

   companion object{

       /**
        * Returns a list of time Points which where written from the time of calling the method down the given number of days.
        * Example If you input "5" as an argument, you get all time Points from the last 5 days.
        */
       @RequiresApi(Build.VERSION_CODES.O)
       fun getListOfTimePointsSince(days : Int) : MutableList<TimePoint>{
           if(days < 1){
               throw Exception("Tried to give $days, which is not enough");
           }

           //init
           var result : MutableList<TimePoint> = mutableListOf<TimePoint>();
           val allowedMilisecondOffset = days * 24 * 60 * 60 * 1000
           val dateNow = System.currentTimeMillis() - allowedMilisecondOffset

           //opens the csv file and skips the header
           val reader : BufferedReader = File(TimeKeeperModel.CSVPath).bufferedReader()
           var line = reader.readLine()
           line = reader.readLine()

           //actual reading
           while(line != null){
               val entries : List<String> = line.split(",")
               var id : String = entries[0]
               var milSecs : Long = entries[1].toLong()
               if(dateNow >= milSecs){ //TimePoint is within range
                   result.add(TimePoint(milSecs, id))
               }else{
                   break;
               }
               reader.readLine()
           }

           return result;
       }

       /**
        * Saves a given Timepoint to the file.
        */
       fun saveTimePoint(point : TimePoint){


       }
   }
}