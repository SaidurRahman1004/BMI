package com.example.bmicald

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bmiTextView: TextView = findViewById(R.id.bmitv)
        val conditionTextView: TextView = findViewById(R.id.cnd)
        val recalculateButton: Button = findViewById(R.id.recalculate_button) //new button

        val bmiScore = intent.getDoubleExtra("EXTRA_BMI_SCORE", 0.0)
        val bmiCondition = intent.getStringExtra("EXTRA_BMI_CONDITION")

        bmiTextView.text = String.format("%.1f", bmiScore)
        conditionTextView.text = bmiCondition

        recalculateButton.setOnClickListener {
            finish()
        }
    }
}