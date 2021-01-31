package com.example.timekeeper

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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

        //sets up the button
        val PlayButton : Button = findViewById(R.id.playButton)
        PlayButton.setBackgroundColor(Color.GREEN)
        PlayButton.setOnClickListener(){
            buttonClicked()
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