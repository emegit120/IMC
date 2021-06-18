package br.com.imc

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.content.ContextCompat
import br.com.imc.extensions.format
import br.com.imc.watchers.DecimalTextWatcher

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
            etAltura.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }
    }

    private fun novoCalculo(){
        etPeso.setText("0")
        etAltura.setText("0")
    }

    private fun limparCalculo(){
        tvIMC.text = ""
        ivIMCStatus.setImageResource(0)
        tvIMCStatus.text = ""
    }

    private fun calcular() {

        limparCalculo()

        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        if(peso == 0.0){
            etPeso.error = "informar o Peso"
            Toast.makeText(this, "Favor informar o Peso", Toast.LENGTH_SHORT).show()
        } else if(altura == 0.0){
            etAltura.error = "informar o Altura"
            Toast.makeText(this, "Favor informar o Altura", Toast.LENGTH_SHORT).show()
        }else {

            val imc = peso / (altura * altura)

            when (imc) {
                in 0.0..18.5 -> configuraIMC(imc, R.drawable.masc_abaixo, R.string.magreza)
                in 18.6..24.9 -> configuraIMC(imc, R.drawable.masc_ideal, R.string.peso_normal)
                in 25.0..29.9 -> configuraIMC(imc, R.drawable.masc_sobre, R.string.sobre_peso)
                in 30.0..34.9 -> configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_i)
                in 35.0..39.9 -> configuraIMC(
                    imc,
                    R.drawable.masc_obeso,
                    R.string.obesidade_grau_ii
                )
                else -> configuraIMC(
                    imc,
                    R.drawable.masc_extremo_obeso,
                    R.string.obesidade_grau_iii
                )
            }
            novoCalculo()
        }

    }

    private fun configuraIMC(imc:Double, drawableId: Int, stringId: Int) {
        tvIMC.text = getString(R.string.status_imc, imc.format(2))
        ivIMCStatus.setImageDrawable(ContextCompat.getDrawable(this, drawableId))
        tvIMCStatus.text = getString(stringId)

    }

    private fun setUpView() {
        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        btCalcular = findViewById(R.id.btCalcular)
        tvIMC = findViewById(R.id.tvIMC)
        tvIMCStatus = findViewById(R.id.tvIMCStatus)
        ivIMCStatus = findViewById(R.id.ivIMCStatus)

        etPeso.addTextChangedListener(DecimalTextWatcher(etPeso, 1))
        etAltura.addTextChangedListener(DecimalTextWatcher(etAltura))
    }
}