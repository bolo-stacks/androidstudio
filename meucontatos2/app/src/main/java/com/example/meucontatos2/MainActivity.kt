package com.example.meucontatos2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var contatos: ArrayList<Contato> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        carregarContatos()

        val contatoAdapter = ContatoAdapter(this, contatos)
        listView.adapter = contatoAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val contato = contatos[position]
            val intent = Intent(this, ContactDetailsActivity::class.java).apply {
                putExtra("contato", contato)
            }
            startActivity(intent)
        }
    }

    private fun carregarContatos() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MeuContatos", MODE_PRIVATE)
        val nome = sharedPreferences.getString("contato_nome", "N/A")
        val telefone = sharedPreferences.getString("contato_telefone", "N/A")
        val email = sharedPreferences.getString("contato_email", "N/A")
        val endereco = sharedPreferences.getString("contato_endereco", "N/A")

        val contato = Contato(nome ?: "N/A", telefone ?: "N/A", email ?: "N/A", endereco ?: "N/A")
        contatos.add(contato)
    }
}
