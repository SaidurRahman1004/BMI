package com.example.bmicald

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val weightInput: EditText = findViewById(R.id.inp1w)
        val heightFeetInput: EditText = findViewById(R.id.inp_height_ft)
        val heightInchInput: EditText = findViewById(R.id.inp_height_in)
        val calculateButton: Button = findViewById(R.id.calb)

        calculateButton.setOnClickListener {
            val weightStr = weightInput.text.toString()
            val feetStr = heightFeetInput.text.toString()
            val inchStr = heightInchInput.text.toString()

            if (weightStr.isEmpty() || feetStr.isEmpty() || inchStr.isEmpty()) {
                Toast.makeText(this, "অনুগ্রহ করে সব তথ্য দিন", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weightKg = weightStr.toDouble()
            val heightFeet = feetStr.toDouble()
            val heightInch = inchStr.toDouble()


            val totalInches = (heightFeet * 12) + heightInch

            val heightMeters = totalInches * 0.0254

            val bmi = weightKg / (heightMeters * heightMeters)

            val condition = getBmiCondition(bmi)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("EXTRA_BMI_SCORE", bmi)
            intent.putExtra("EXTRA_BMI_CONDITION", condition)
            startActivity(intent)
        }
    }

    private fun getBmiCondition(bmi: Double): String {
        return when {
            bmi < 18.5 -> "কম ওজন (Underweight)"
            bmi >= 18.5 && bmi < 25.0 -> "স্বাস্থ্যকর ওজন (Healthy Weight)"
            bmi >= 25.0 && bmi < 30.0 -> "অতিরিক্ত ওজন (Overweight)"
            bmi >= 30.0 && bmi < 35.0 -> "স্থূলতা (Obesity Class I)"
            bmi >= 35.0 && bmi < 40.0 -> "২য় পর্যায়ের স্থূলতা (Obesity Class II)"
            else -> "৩য় পর্যায়ের স্থূলতা (Obesity Class III)"
        }
    }
}