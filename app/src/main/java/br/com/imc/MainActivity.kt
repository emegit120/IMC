package br.com.imc

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var etPeso: EditText
    lateinit var etAltura: EditText
    lateinit var btCalcular: Button
    lateinit var tvIMC: TextView
    lateinit var tvIMCStatus: TextView
    lateinit var ivIMCStatus: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()

        btCalcular.setOnClickListener{
            calcular()
        }
    }

    private fun calcular() {
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        val imc = peso / (altura * altura)

    }

    private fun setUpView() {
        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        btCalcular = findViewById(R.id.btCalcular)
        tvIMC = findViewById(R.id.tvIMC)
        tvIMCStatus = findViewById(R.id.tvIMCStatus)
        ivIMCStatus = findViewById(R.id.ivIMCStatus)
    }
}