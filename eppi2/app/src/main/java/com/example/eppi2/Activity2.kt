package com.example.eppi2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar) // Layout onde os nomes e telefones serão exibidos

        // Referência à TextView e Botões
        val textViewNamesList = findViewById<TextView>(R.id.textViewNamesList)
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        // Acessa as SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Recupera a lista de contatos (nome e telefone) salva nas SharedPreferences
        val contactsSet = sharedPreferences.getStringSet("contactsList", mutableSetOf())

        // Exibe a lista de contatos na TextView
        updateContactsList(textViewNamesList, contactsSet)

        // Configura o botão "Voltar"
        buttonBack.setOnClickListener {
            finish() // Fecha a Activity atual e retorna à anterior
        }
    }

    // Função para atualizar a lista de contatos (nome e telefone) na TextView
    private fun updateContactsList(textView: TextView, contactsSet: Set<String>?) {
        val contactsList = contactsSet?.joinToString("\n") ?: "Nenhum contato adicionado"
        textView.text = contactsList
    }
}
