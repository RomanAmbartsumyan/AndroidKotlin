package com.example.timer

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var seconds = 0
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart.setOnClickListener {
            if(!isRunning) {
                isRunning = true
                runTimer()
            }
        }

        buttonPause.setOnClickListener {
            isRunning = false
        }

        buttonReset.setOnClickListener {
            isRunning = false
            seconds = 0
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_RUNNING, isRunning)
        outState.putInt(SECONDS, seconds)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        seconds = savedInstanceState.getInt(SECONDS)
        isRunning = savedInstanceState.getBoolean(IS_RUNNING)
        if(isRunning){
            runTimer()
        }
    }

    private fun runTimer() {
        val handler = Handler()

        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d",
                    hours,
                    minutes,
                    secs
                )

                textViewTimer.text = time

                if (isRunning) {
                    seconds++
                    handler.postDelayed(this, 1000)
                } else {
                    handler.removeCallbacks(this)
                }
            }
        })
    }

    companion object {
        const val SECONDS = "SECONDS"
        const val IS_RUNNING = "IS_RUNNING"
    }
}