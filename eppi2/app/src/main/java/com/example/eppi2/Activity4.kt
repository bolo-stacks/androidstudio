package com.example.eppi2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class Activity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_limpar)

        // Referência ao ListView e aos Botões
        val listViewContacts = findViewById<ListView>(R.id.listViewContacts)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        // Acessa as SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Recupera a lista de contatos salva nas SharedPreferences
        val namesSet = sharedPreferences.getStringSet("contactsList", mutableSetOf())

        // Converte o conjunto de contatos para uma lista
        val contactsList = namesSet?.toList() ?: listOf()

        // Verifica se a lista está vazia
        if (contactsList.isEmpty()) {
            Toast.makeText(this, "Nenhum contato disponível.", Toast.LENGTH_SHORT).show()
        }

        // Configura o ListView com os contatos
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)
        listViewContacts.adapter = adapter

        // Configuração do botão "Limpar tudo"
        buttonClear.setOnClickListener {
            val editor = sharedPreferences.edit()

            // Limpa todos os dados armazenados
            editor.clear()
            editor.apply()

            // Atualiza a lista de contatos no ListView
            adapter.clear()

            // Mostra uma mensagem informando que os dados foram apagados
            Toast.makeText(this, "Todos os dados foram apagados!", Toast.LENGTH_SHORT).show()
        }

        // Configuração do botão "Voltar"
        buttonBack.setOnClickListener {
            finish() // Fecha a Activity atual e retorna à anterior
        }

        // Configuração para excluir um contato
        listViewContacts.setOnItemClickListener { parent, view, position, id ->
            val selectedContact = parent.getItemAtPosition(position).toString()

            // Cria o diálogo de confirmação para excluir
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Excluir contato")
                .setMessage("Você tem certeza que deseja excluir o contato $selectedContact?")
                .setPositiveButton("Sim") { dialog, which ->
                    // Remove o contato da lista
                    namesSet?.remove(selectedContact)

                    // Atualiza os SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putStringSet("contactsList", namesSet)
                    editor.apply()

                    // Atualiza a lista no ListView
                    val updatedList = namesSet?.toList() ?: listOf()
                    adapter.clear()
                    adapter.addAll(updatedList)

                    // Mostra mensagem de sucesso
                    Toast.makeText(this, "Contato excluído com sucesso!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Não", null)
                .show()
        }
    }
}
