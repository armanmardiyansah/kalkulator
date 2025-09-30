package com.example.tugas_maryan

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btnDot: Button = findViewById(R.id.btnDot)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnMinus: Button = findViewById(R.id.btnMinus)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnEquals: Button = findViewById(R.id.btnEquals)

        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (btn in numberButtons) {
            btn.setOnClickListener {
                if (tvResult.text.toString() == "0") {
                    tvResult.text = btn.text
                } else {
                    tvResult.append(btn.text)
                }
            }
        }

        btnDot.setOnClickListener {
            if (!tvResult.text.contains(".")) {
                tvResult.append(".")
            }
        }

        btnClear.setOnClickListener {
            tvResult.text = "0"
        }

        val operators = listOf(btnPlus, btnMinus, btnMultiply, btnDivide)

        for (op in operators) {
            op.setOnClickListener {
                val lastChar = tvResult.text.lastOrNull()
                if (lastChar != null && lastChar !in listOf('+', '-', '×', '÷')) {
                    tvResult.append(op.text)
                }
            }
        }

        btnEquals.setOnClickListener {
            try {
                var expression = tvResult.text.toString()
                expression = expression.replace('÷', '/')
                expression = expression.replace('×', '*')
                expression = expression.replace('−', '-')

                val result = ExpressionBuilder(expression).build().evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            } catch (e: Exception) {
                tvResult.text = "Error"
            }
        }
    }
}