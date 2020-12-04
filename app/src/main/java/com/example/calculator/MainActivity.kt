package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import calcHelpers.RPNBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView;
    private var inputs : ArrayList<String> = ArrayList<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput);

    }

    fun btnClicked(view: View) {
        var btnClicked = view as Button;



        when(btnClicked.text) {
            "=" -> {
                if(inputs == null || inputs.count() == 0) {
                    return;
                }

                var answer = RPNBuilder.EvaluateRPN(inputs.toTypedArray());
                tvInput.text = answer.toString()
            }
            "CE" -> {
                tvInput.text = "";
            }
            else -> {
                var newText = "${tvInput.text}" + "${btnClicked.text}";
                inputs.add(btnClicked.text.toString());
                tvInput.text = newText;
            }
        }
    }

}