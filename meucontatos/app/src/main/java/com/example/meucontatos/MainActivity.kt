package com.example.meucontatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.meucontatos.R
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meucontatos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addContact = findViewById<TextView>(R.id.tvAddContact)
        val viewContacts = findViewById<TextView>(R.id.tvViewContacts)
        val deleteContacts = findViewById<TextView>(R.id.tvDeleteContacts)

        addContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        viewContacts.setOnClickListener {
            startActivity(Intent(this, ViewContactsActivity::class.java))
        }

        deleteContacts.setOnClickListener {
            startActivity(Intent(this, DeleteContactsActivity::class.java))
        }
    }
}
