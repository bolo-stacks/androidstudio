package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EuroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_euro)

        // Referências aos elementos do layout, substituindo TextViews e EditText por View
        val viewValorReal = findViewById<EditText>(R.id.etValorReal)  // Aqui mantivemos o EditText
        val viewResultado = findViewById<View>(R.id.viewResultado)  // Área de visualização do resultado
        val viewCalcular = findViewById<View>(R.id.viewCalcular)  // Área de clique para calcular

        // Carrega a taxa de conversão do SharedPreferences
        val prefs = getSharedPreferences("taxas", MODE_PRIVATE)
        val taxaEuro = prefs.getFloat("taxaEuro", 5.0f) // Valor padrão se não existir a taxa salva

        // Configura o clique para realizar o cálculo
        viewCalcular.setOnClickListener {
            val valorReal = viewValorReal.text.toString().toFloatOrNull()
            if (valorReal != null) {
                val resultado = calcularConversao(valorReal, taxaEuro)
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
    private fun mostrarResultado(view: View, resultado: String) {
        // Certifique-se de que a View contém um TextView para exibir o texto
        val textView = view.findViewById<TextView>(R.id.tvResultado)
        if (textView != null) {
            textView.text = "Resultado: $resultado"
        } else {
            // Caso o TextView não seja encontrado, você pode optar por lançar uma exceção ou tratar de outra forma
            throw IllegalArgumentException("View fornecida não contém um TextView válido para exibir o resultado.")
        }
    }

}
