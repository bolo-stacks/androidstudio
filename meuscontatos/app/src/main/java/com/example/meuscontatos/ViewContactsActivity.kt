package com.example.meuscontatos;

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criando um ScrollView para exibir os contatos
        val scrollView = ScrollView(this)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // Recuperando contatos dos SharedPreferences
        val sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
        val allContacts = sharedPreferences.all

        // Adicionando os contatos ao layout
        if (allContacts.isNotEmpty()) {
            allContacts.forEach { (name, phone) ->
                val contactView = TextView(this).apply {
                    text = "$name: $phone"
                    textSize = 18f
                    setPadding(0, 8, 0, 8)
                }
                layout.addView(contactView)
            }
        } else {
            val noContactsView = TextView(this).apply {
                text = "Nenhum contato salvo!"
                textSize = 18f
                gravity = android.view.Gravity.CENTER
            }
            layout.addView(noContactsView)
        }

        // Adicionando o layout ao ScrollView
        scrollView.addView(layout)

        // Definindo o ScrollView como o conteúdo da atividade
        setContentView(scrollView)
    }
}
