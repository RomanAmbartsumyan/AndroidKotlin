package com.example.coffeshop

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        welcomeClient()
        coffee()
        tea()

        buttonOrder.setOnClickListener {
            val thirdActivity = Intent(this, ThirdActivity::class.java)
            startActivity(thirdActivity)
        }
    }

    private fun welcomeClient() {
        val name = intent.getStringExtra(MainActivity.NAME)
        textViewWelcome.text = "Привет, $name!"
    }

    private fun coffee() {
        radioButtonCoffee.setOnClickListener {
            checkBoxLemon.visibility = View.GONE
            textViewDrink.text = "Что пожожить в Ваш кофе?"
            spinnerCoffee.visibility = View.VISIBLE
            spinnerTea.visibility = View.GONE
        }
    }

    private fun tea() {
        radioButtonTea.setOnClickListener {
            checkBoxLemon.visibility = View.VISIBLE
            textViewDrink.text = "Что пожожить в Ваш чай?"
            spinnerTea.visibility = View.VISIBLE
            spinnerCoffee.visibility = View.GONE
        }
    }
}