package com.example.meucontatos2

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListContactsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter
    private val contactsList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_contacts)

        recyclerView = findViewById(R.id.recyclerViewContacts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadContacts()

        adapter = ContactAdapter(contactsList)
        recyclerView.adapter = adapter
    }

    private fun loadContacts() {
        val sharedPreferences = getSharedPreferences("contacts", MODE_PRIVATE)
        val allEntries = sharedPreferences.all

        for ((key, value) in allEntries) {
            if (key.endsWith("_nome")) {
                val id = key.replace("_nome", "")
                val nome = value as String
                val telefone = sharedPreferences.getString("$id_telefone", "") ?: ""
                val email = sharedPreferences.getString("$id_email", "") ?: ""
                val endereco = sharedPreferences.getString("$id_endereco", "") ?: ""

                val contato = Contact(nome, telefone, email, endereco)
                contactsList.add(contato)
            }
        }
        adapter.notifyDataSetChanged()
    }
}
