package com.example.contactsapp

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.meucontatos.R


class ViewContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contacts)

        val tvContacts = findViewById<TextView>(R.id.tvContacts)
        val sharedPref = getSharedPreferences("contacts", Context.MODE_PRIVATE)
        val contacts = sharedPref.getStringSet("contact_list", mutableSetOf())

        tvContacts.text = contacts?.joinToString("\n") ?: "Nenhum contato armazenado."
    }
}
