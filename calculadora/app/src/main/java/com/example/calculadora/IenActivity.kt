package com.example.calculadora

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ien)

        // Referências aos elementos do layout
        val etValorReal = findViewById<EditText>(R.id.etValorReal)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val tvCalcular = findViewById<TextView>(R.id.tvCalcular)

        // Carrega a taxa de conversão do SharedPreferences
        val prefs = getSharedPreferences("taxas", MODE_PRIVATE)
        val taxaIen = prefs.getFloat("taxaIen", 5.0f) // Valor padrão caso a taxa não exista

        // Configura o clique para realizar o cálculo
        tvCalcular.setOnClickListener {
            val valorReal = etValorReal.text.toString().toFloatOrNull()
            if (valorReal != null) {
                val resultado = calcularConversao(valorReal, taxaIen)
                tvResultado.text = getString(R.string.resultado_ien, resultado) // Uso de string formatada
            } else {
                tvResultado.text = getString(R.string.valor_invalido)
            }
        }
    }

    // Função para realizar o cálculo de conversão
    private fun calcularConversao(valorReal: Float, taxa: Float): Float {
        return valorReal / taxa
    }
}
