package com.example.meucontatos3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListarContatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_contatos)

        val contatosTextView = findViewById<TextView>(R.id.contatosTextView)

        val contatos = SharedPreferencesHelper.getContatos(this)
        contatosTextView.text = contatos.joinToString("\n") { it.toString() }
    }
}
