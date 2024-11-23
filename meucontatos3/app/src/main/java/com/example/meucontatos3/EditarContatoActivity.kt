package com.example.meucontatos3

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditarContatoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contato)

        val nomeEditText = findViewById<EditText>(R.id.nomeEditText)
        val foneEditText = findViewById<EditText>(R.id.foneEditText)
        val apelidoEditText = findViewById<EditText>(R.id.apelidoEditText)

        // Implementação para carregar os dados do contato existente e permitir edição.
    }
}
