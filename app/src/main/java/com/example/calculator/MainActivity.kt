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
        var newText = "${tvInput.text}" + "${btnClicked.text}";


        if(btnClicked.text == "=") {
            var rpnText = RPNBuilder().InfixToRPN(inputs.toTypedArray())
            var answer = RPNBuilder().EvaluateRPN(rpnText);
            tvInput.text = answer.toString()
            println(rpnText)
        } else {
            inputs.add(btnClicked.text.toString());
            tvInput.text = newText;
        }
    }

}