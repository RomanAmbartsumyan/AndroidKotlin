package com.example.coffeshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isButtonEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validText(editTextPassword, editTextPersonName)
        validText(editTextPersonName, editTextPassword)
        buttonEnter.setOnClickListener {
            val secondActivity = Intent(this, SecondActivity::class.java)
            val name = editTextPersonName.text.toString()
            secondActivity.putExtra(NAME, name)
            startActivity(secondActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_BUTTON_ENABLE, isButtonEnable)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        buttonEnter.isEnabled = savedInstanceState.getBoolean(IS_BUTTON_ENABLE)
    }

    private fun validText(editText1: EditText, editText2: EditText) {
        editText1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonEnter.isEnabled = editText1.text.isNotBlank() && editText2.text.isNotBlank()
                isButtonEnable = buttonEnter.isEnabled
            }
        })
    }

    companion object{
        const val IS_BUTTON_ENABLE = "IS_BUTTON_ENABLE"
        const val NAME = "NAME"
    }
}