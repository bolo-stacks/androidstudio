package com.example.contactsapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import com.example.meucontatos.R


class AddContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val tvSaveContact = findViewById<TextView>(R.id.tvSaveContact)

        tvSaveContact.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val sharedPref = getSharedPreferences("contacts", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                val currentContacts = sharedPref.getStringSet("contact_list", mutableSetOf()) ?: mutableSetOf()
                currentContacts.add("$name - $phone")
                editor.putStringSet("contact_list", currentContacts)
                editor.apply()

                finish() // Voltar para a tela anterior
            }
        }
    }
}
