package com.example.timekeeper

import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.timekeeper.internal.FileWorker
import com.example.timekeeper.internal.TimeKeeperModel
import com.example.timekeeper.internal.TimePoint
import java.sql.Time
import java.util.*

class MainActivity : AppCompatActivity() {

    var Model : TimeKeeperModel? = null

    val clockNames: List<String> = listOf<String>(
            "Today: ",
            "This Week: ",
            "This Month: "
    )
    var clockOffset : Long = 0L
    var clocks: MutableList<TextClock> = mutableListOf<TextClock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sets up the button
        val PlayButton : Button = findViewById(R.id.playButton)
        PlayButton.setBackgroundColor(Color.GREEN)
        PlayButton.setOnClickListener(){
            buttonClicked()
        }

        //sets up the clocks
        val clockListView: LinearLayout = findViewById(R.id.clockList)
        for(clockName in clockNames){
            //label
            val newClock : TextView = TextView(applicationContext)
            newClock.text = clockName
            newClock.setTextSize(resources.getDimension(R.dimen.clockHeaderTextSize))
            newClock.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            newClock.setLayoutParams(ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT))
            clockListView.addView(newClock)

            //actual clock
            val newClockRepresentation : TextClock = TextClock(applicationContext)
            clocks.add(newClockRepresentation)
            newClockRepresentation.setLayoutParams(ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT))
            newClockRepresentation.setTextSize(resources.getDimension(R.dimen.clockTextSize))
            newClockRepresentation.gravity = Gravity.CENTER_VERTICAL
            clockListView.addView(newClockRepresentation)
        }
    }

    override fun onStart() {
        super.onStart()
        Model = TimeKeeperModel(this)
        setUpClocks()
    }

    /**
     * Changes buttons color, starts and stops the clocks on screen
     */
    fun buttonClicked(){
        val button : Button = findViewById(R.id.playButton)

        if(Model!!.color == Color.GREEN){
            Model!!.color = Color.RED
            button.setBackgroundColor(Model!!.color)
            button.setText(R.string.button_pause)
            //TODO start clocks
        }else{
            Model!!.color =  Color.GREEN
            button.setBackgroundColor(Model!!.color)
            button.setText(R.string.button_play)
            stopClocks()
        }
    }

    /**
     * TODO gets the timePoints of a given range from the saved points and applies their value to the clocks
     * Important, only request the number of days of the current month for the month.
     * The number of days for the week (less if a full week was not completed this month)
     * The time for the current day in order to get the real deal.
     */
    fun setUpClocks(){

    }

    /**
     * TODO starts the service that
     */
    fun StartClocks(){

    }

    /**
     * Saves a new TimePoint and resets the offset.
     */
    fun stopClocks(){
        val newPoint = TimePoint(
                getRandomizedID(),
                System.currentTimeMillis(),
                clockOffset
        )
        FileWorker.addTimePoint(newPoint)
        clockOffset = 0L
    }


    /**
     * TODO implement me properly to generate random IDs for timePoints.
     */
    fun getRandomizedID(): String {
        return "randomID"
    }

    /**
     * Testing method, only in here for debugging.
     * Date from Java has the right miliseconds (i checked), but it interprets them wrong.
     * Not even kidding.
     */
    fun testCSVReader(){
        val d = Date()
        FileWorker.addTimePoint(TimePoint("testing_read_and_write", System.currentTimeMillis(), 10L))
        var timePoints = FileWorker.getListOfTimePointsSince(1)
        return
    }


    /*
     * TODO think about a possible automatic start and stop of the clocks when the real clock strikes 0:00 to keep up consistency.
     */
}