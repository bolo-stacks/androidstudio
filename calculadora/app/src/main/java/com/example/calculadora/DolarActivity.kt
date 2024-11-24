
package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DolarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dolar)

        // Referências aos elementos do layout, substituindo TextViews e EditText por View
        val viewValorReal = findViewById<EditText>(R.id.etValorReal)  // Aqui mantivemos o EditText
        val viewResultado = findViewById<View>(R.id.viewResultado)  // Área de visualização do resultado
        val viewCalcular = findViewById<View>(R.id.viewCalcular)  // Área de clique para calcular

        // Carrega a taxa de conversão do SharedPreferences
        val prefs = getSharedPreferences("taxas", MODE_PRIVATE)
        val taxaDolar = prefs.getFloat("taxaDolar", 5.0f) // Valor padrão se não existir a taxa salva

        // Configura o clique para realizar o cálculo
        viewCalcular.setOnClickListener {
            val valorReal = viewValorReal.text.toString().toFloatOrNull()
            if (valorReal != null) {
                val resultado = calcularConversao(valorReal, taxaDolar)
                // Exibe o resultado usando um View
                mostrarResultado(viewResultado, resultado)
            } else {
                mostrarResultado(viewResultado, "Valor inválido")
            }
        }
    }

    // Função para realizar o cálculo de conversão
    private fun calcularConversao(valorReal: Float, taxa: Float): Float {
        return valorReal / taxa
    }

    // Função para mostrar o resultado na View
    private fun mostrarResultado(view: View, resultado: Any) {
        // Aqui você pode customizar a maneira de exibir o resultado, como atualizar o texto em um TextView oculto ou alterar uma propriedade da View
        if (view is TextView) {
            view.text = "Resultado: $resultado"
        }
    }
}
