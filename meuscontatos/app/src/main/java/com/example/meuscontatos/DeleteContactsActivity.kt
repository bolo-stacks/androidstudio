package com.example.meuscontatos;

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criando o layout principal
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            setPadding(16, 16, 16, 16)
        }

        // Botão para excluir contatos
        val deleteButton = TextView(this).apply {
            text = "Excluir Todos os Contatos"
            textSize = 18f
            setPadding(16, 16, 16, 16)
            setBackgroundColor(android.graphics.Color.RED)
            setTextColor(android.graphics.Color.WHITE)
            gravity = android.view.Gravity.CENTER
        }

        // Ação ao clicar no botão de excluir
        deleteButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            Toast.makeText(this, "Todos os contatos foram excluídos!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Adicionando o botão ao layout
        layout.addView(deleteButton)

        // Definindo o layout como conteúdo da atividade
        setContentView(layout)
    }
}
