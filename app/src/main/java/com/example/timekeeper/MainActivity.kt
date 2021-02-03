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
import java.util.*

class MainActivity : AppCompatActivity() {

    var Model : TimeKeeperModel? = null

    val clockNames: List<String> = listOf<String>(
            "Today: ",
            "This Week: ",
            "This Month: "
    )

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
            newClock.setLayoutParams(ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT))
            clockListView.addView(newClock)

            //actual clock
            val newClockRepresentation : TextClock = TextClock(applicationContext)
            clocks.add(newClockRepresentation)
            newClockRepresentation.setLayoutParams(ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT))
            newClockRepresentation.setTextSize(resources.getDimension(R.dimen.clockTextSize))
            newClockRepresentation.gravity
            clockListView.addView(newClockRepresentation)
        }
    }

    override fun onStart() {
        super.onStart()
        Model = TimeKeeperModel(this)
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
        }else{
            Model!!.color =  Color.GREEN
            button.setBackgroundColor(Model!!.color)
            button.setText(R.string.button_play)
        }
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