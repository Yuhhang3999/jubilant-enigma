package com.example.assignment1

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var inputValue: EditText
    private lateinit var resultValue: TextView
    private lateinit var buttonConvert: Button
    private lateinit var spinnerConversionType: Spinner
    private var conversionType: String = "cm to inches" // Default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputValue = findViewById(R.id.input_value)
        resultValue = findViewById(R.id.result_value)
        buttonConvert = findViewById(R.id.button_convert)
        spinnerConversionType = findViewById(R.id.spinner_conversion_type)

        val conversionOptions = arrayOf("cm to inches", "kg to pounds")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerConversionType.adapter = adapter

        spinnerConversionType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                conversionType = conversionOptions[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        buttonConvert.setOnClickListener {
            convertValue()
        }
    }

    private fun convertValue() {
        val valueStr = inputValue.text.toString()
        if (valueStr.isNotEmpty()) {
            try {
                val value = valueStr.toDouble()
                val result = when (conversionType) {
                    "cm to inches" -> value / 2.54
                    "kg to pounds" -> value * 2.20462
                    else -> 0.0
                }
                resultValue.text = String.format("Result: %.2f", result)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }
}
