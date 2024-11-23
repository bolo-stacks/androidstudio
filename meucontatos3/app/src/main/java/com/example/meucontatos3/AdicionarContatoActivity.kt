package com.example.meucontatos3

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AdicionarContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_contato)

        val nomeEditText = findViewById<EditText>(R.id.nomeEditText)
        val foneEditText = findViewById<EditText>(R.id.foneEditText)
        val apelidoEditText = findViewById<EditText>(R.id.apelidoEditText)

        nomeEditText.setOnEditorActionListener { _, _, _ ->
            val contato = Contato(
                nome = nomeEditText.text.toString(),
                telefone = foneEditText.text.toString(),
                apelido = apelidoEditText.text.toString()
            )
            SharedPreferencesHelper.adicionarContato(this, contato)
            finish() // Fecha a activity após adicionar
            true
        }
    }
}
