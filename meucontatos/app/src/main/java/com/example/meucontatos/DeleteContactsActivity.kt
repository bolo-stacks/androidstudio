package com.example.meucontatos

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.meucontatos.R


class DeleteContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_contacts)

        val tvDeleteAll = findViewById<TextView>(R.id.tvDeleteAll)

        tvDeleteAll.setOnClickListener {
            val sharedPref = getSharedPreferences("contacts", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            finish() // Voltar para a tela anterior
        }
    }
}
