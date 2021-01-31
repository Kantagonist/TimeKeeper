package com.example.timekeeper.internal

import android.os.Build
import android.util.Log
import android.view.Display
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.IOException


/**
 * Singleton class which creates the necessary csv Files to save TimePoints and work amounts
 * Unlike Java, Singletons in Kotlin do not take arguments.
 * They also only use static methods and are instantiated upon first usage of a method, so we require the user to use a set up method first, otherwise, we throw an error
 */
object FileWorker {

    private var PathToAppDir : String? = null

    private var Model : TimeKeeperModel? = null

    private val UsageDir : String = "time_point_records"
    private val TimePointCsv :String = "clock_in_records.csv"
    private val WorkShiftCsv : String = "work_shift.csv"

    init{
        //no init because kotlin is wierd with singletons
    }

    /**
     * Replaces Javas getInstance() Pattern.
     * Sets model and creates the csv files if necessary.
     */
    fun setUpInstance(model: TimeKeeperModel){

        //instatiate paths
        Model = model
        PathToAppDir = Model!!.AppContext.filesDir.absolutePath

        //checks if dir is set up
        var workingDir = File(PathToAppDir + "/" + UsageDir)
        if(!workingDir.isDirectory)workingDir.mkdir()
        var timePointCSV = File(PathToAppDir + "/" +  UsageDir + "/" + TimePointCsv)
        if(!timePointCSV.isFile)timePointCSV.createNewFile()
        var workShiftCSV = File(PathToAppDir + "/" + UsageDir + "/" + WorkShiftCsv)
        if(!workShiftCSV.isFile)workShiftCSV.createNewFile()
    }


    /**
     * Adds a new TimePoint on top of the csv file.
     */
    fun addTimePoint(point : TimePoint){
        if(Model == null) throwUninstantiateError()

        var fileWriter : FileWriter? = null
        try{
            fileWriter = FileWriter(PathToAppDir + "/" +  UsageDir + "/" + TimePointCsv)
            fileWriter.append(point.Id)
            fileWriter.append(',')
            fileWriter.append("${point.TimeInMiliseconds}")
            fileWriter.append("\n")
            Log.d("FILE WRITING", " added ${point.toString()} ")
        } catch (e: Exception) {
            println("Writing CSV error!")
            e.printStackTrace()

        } finally {
            try {
                fileWriter!!.flush()
                fileWriter.close()
            } catch (e: IOException) {
                println("Flushing/closing error!")
                e.printStackTrace()
            }
        }
    }

    /**
     * Returns a list of time Points which where written from the time of calling the method down the given number of days.
     * Example If you input "5" as an argument, you get all time Points from the last 5 days.
     */
    fun getListOfTimePointsSince(days : Int) : MutableList<TimePoint>{
        if(Model == null) throwUninstantiateError()

        if(days < 1){
            throw Exception("Tried to give $days, which is not enough");
        }

        //init
        var result : MutableList<TimePoint> = mutableListOf<TimePoint>();
        val allowedMilisecondOffset = days * 24 * 60 * 60 * 1000
        val dateNow = System.currentTimeMillis() - allowedMilisecondOffset

        //opens the csv file, we have no header
        val reader : BufferedReader = File(PathToAppDir + "/" +  UsageDir + "/" + TimePointCsv).bufferedReader()
        var line = reader.readLine()

        //actual reading
        while(line != null){
            val entries : List<String> = line.split(",")
            var id : String = entries[0]
            var milSecs : Long = entries[1].toLong()
            if(dateNow <= milSecs){ //TimePoint is within range
                val point = TimePoint(id, milSecs)
                result.add(point)
                Log.d("READING TIME POINTS", "read ${point.toString()}")
            }else{
                break;
            }
            line = reader.readLine()
        }
        reader.close()
        return result;
    }

    //TODO create read an write for lines : <day-ofWeek-ofYear>, milisecondsSpend>



    /**
     * Deletes all entries
     */
    public fun flushStorage(){
        var timePointCSV = File(PathToAppDir + "/" +  UsageDir + "/" + TimePointCsv)
        var workShiftCSV = File(PathToAppDir + "/" + UsageDir + "/" + WorkShiftCsv)
    }

    private fun throwUninstantiateError() {
        throw Error("tried to use the singleton FileWorker without instantiating it.")
    }
}