package com.example.meucontatos2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewAddContact: TextView
    private lateinit var textViewListContacts: TextView
    private lateinit var textViewExportContacts: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewAddContact = findViewById(R.id.textViewAddContact)
        textViewListContacts = findViewById(R.id.textViewListContacts)
        textViewExportContacts = findViewById(R.id.textViewExportContacts)

        // Navegação para adicionar contato
        textViewAddContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        // Navegação para listar contatos
        textViewListContacts.setOnClickListener {
            val intent = Intent(this, ListContactsActivity::class.java)
            startActivity(intent)
        }

        // Navegação para exportar contatos
        textViewExportContacts.setOnClickListener {
            val intent = Intent(this, ExportContactsActivity::class.java)
            startActivity(intent)
        }
    }
}
