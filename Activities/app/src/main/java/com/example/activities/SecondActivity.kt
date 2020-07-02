package com.example.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val message = intent.getStringExtra(MainActivity.TEXT) ?: ""
        val phone = intent.getStringExtra(MainActivity.PHONE) ?: ""
        messageText.text = "Message: $message"
        phoneText.text = "Phone: $phone"

        sendMessage.setOnClickListener {
            sendMessage(message)
        }

        sendEmail.setOnClickListener {
            val arrayAddresses = arrayOf("r9854334307@mail.ru")
            sendEmail(arrayAddresses, message)
        }

        phoneButton.setOnClickListener {
            dialPhoneNumber(phone)
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


    private fun sendMessage(message: String){
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, message)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun sendEmail(addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}