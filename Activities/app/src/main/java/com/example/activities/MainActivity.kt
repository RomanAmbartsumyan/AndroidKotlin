package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendMessage()
    }

    private fun sendMessage() {
        buttonSend.setOnClickListener {
            val secondActivity = Intent(this, SecondActivity::class.java)
            secondActivity.putExtra(TEXT, editMessage.text.toString())
            secondActivity.putExtra(PHONE, editPhone.text.toString())
            startActivity(secondActivity)
        }
    }

    companion object {
        const val TEXT = "TEXT"
        const val PHONE = "PHONE"
    }
}