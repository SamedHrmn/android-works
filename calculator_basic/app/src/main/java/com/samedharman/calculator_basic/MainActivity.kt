package com.samedharman.calculator_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edtInput1: EditText
    lateinit var edtInput2: EditText
    lateinit var btnSum: Button
    lateinit var btnMin: Button
    lateinit var btnDiv: Button
    lateinit var btnMult: Button
    lateinit var btnEq: Button
    lateinit var txtResult: TextView
    var result: Float = 0.0F
    lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        calculate()

    }

    private fun init() {
        edtInput1 = findViewById(R.id.edit_input1)
        edtInput2 = findViewById(R.id.edit_input2)
        btnSum = findViewById(R.id.btn_sum)
        btnMin = findViewById(R.id.btn_minus)
        btnDiv = findViewById(R.id.btn_div)
        btnMult = findViewById(R.id.btn_multp)
        btnEq = findViewById(R.id.btn_equal)
        txtResult = findViewById(R.id.txt_result)

        calculator = Calculator()
    }

    private fun calculate() {
        var input1: Int = 0
        var input2: Int = 0

        btnSum.setOnClickListener {

            if (edtInput1.text.toString().trim().isNotEmpty())
                input1 = edtInput1.text.toString().toInt()
            else
                Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_LONG)
                    .show()


            if (edtInput2.text.toString().trim().isNotEmpty()) {
                input2 = edtInput2.text.toString().toInt()
                result = calculator.sum(
                    num1 = input1,
                    num2 = input2,
                ).toFloat()

                showResult(result)
            } else
                Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_LONG)
                    .show()

        }

        btnMin.setOnClickListener {
            if (edtInput1.text.toString().trim().isNotEmpty())
                input1 = edtInput1.text.toString().toInt()
            else
                Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_LONG)
                    .show()


            if (edtInput2.text.toString().trim().isNotEmpty()) {
                input2 = edtInput2.text.toString().toInt()
                result = calculator.minus(
                    num1 = input1,
                    num2 = input2,
                ).toFloat()

                showResult(result)
            } else
                Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_LONG)
                    .show()


        }

        btnDiv.setOnClickListener {
            if (edtInput1.text.toString().trim().isNotEmpty())
                input1 = edtInput1.text.toString().toInt()


            if (edtInput2.text.toString().trim().isNotEmpty()) {
                input2 = edtInput2.text.toString().toInt()
                result = calculator.divide(
                    num1 = input1.toFloat(),
                    num2 = input2.toFloat(),
                )

                showResult(result)
            }else {
                Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_LONG)
                    .show()
            }

            if (edtInput2.text.toString().equals("0")) {
                Toast.makeText(
                    applicationContext,
                    "Please enter a number different than 0",
                    Toast.LENGTH_LONG
                ).show()
            }


        }

        btnMult.setOnClickListener {
            if (edtInput1.text.toString().trim().isNotEmpty())
                input1 = edtInput1.text.toString().toInt()
            else
                Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_LONG)
                    .show()


            if (edtInput2.text.toString().trim().isNotEmpty()) {
                input2 = edtInput2.text.toString().toInt()
                result = calculator.multiply(
                    num1 = input1,
                    num2 = input2,
                ).toFloat()

                showResult(result)
            } else
                Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_LONG)
                    .show()

        }
    }

    private fun showResult(result: Float) {
        btnEq.setOnClickListener {
            txtResult.text = result.toString()
        }
    }

}