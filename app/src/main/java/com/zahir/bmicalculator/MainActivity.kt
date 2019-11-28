package com.zahir.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.NumberFormatException
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener{
            calculateBMI(it)
        }

        buttonReset.setOnClickListener{
            resetMe(it)
        }
    }

    private fun calculateBMI(view: View){
        hideKeyboard(view)

        try{
            val weight = editTextWeight.text.toString().toDouble()
            val height = (editTextHeight.text.toString().toDouble() / 100).pow(2.0) //to get in meters^2

            val bmi = weight / height
            val str: String

            //imageViewBMI.visibility = View.VISIBLE

            when {
                bmi < 18.5 -> {
                    imageViewBMI.setImageResource(R.drawable.under)
                    str = " (Underweight)"
                    //makeToast("You are underweight!")
                }
                bmi <= 24.9 -> {
                    imageViewBMI.setImageResource(R.drawable.normal)
                    str = " (Normal)"
                    //makeToast("You have normal BMI!")
                }
                else -> {
                    imageViewBMI.setImageResource(R.drawable.over)
                    str = " (Overweight)"
                    //makeToast("You are overweight!")
                }
            }

            textViewResult.text = "BMI : %.2f".format(bmi) + str

        }catch (ex: NumberFormatException){
            makeToast("Please input all required value(s)")
        }
    }

    private fun resetMe(view: View){
        hideKeyboard(view)
        makeToast("Cleared")

        imageViewBMI.setImageResource(R.drawable.empty)
        //imageViewBMI.visibility = View.GONE
        textViewResult.text = null
        editTextHeight.text.clear()
        editTextWeight.text.clear()

        editTextWeight.requestFocus()
    }

    private fun hideKeyboard(view: View){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun makeToast(text: String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}