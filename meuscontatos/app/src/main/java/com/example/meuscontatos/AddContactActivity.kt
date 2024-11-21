package com.example.meuscontatos;

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criando o layout principal
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // Campo de texto para o nome
        val nameInput = EditText(this).apply {
            hint = "Nome"
        }

        // Campo de texto para o telefone
        val phoneInput = EditText(this).apply {
            hint = "Telefone"
            inputType = android.text.InputType.TYPE_CLASS_PHONE
        }

        // Botão de salvar (TextView usado como botão)
        val saveButton = TextView(this).apply {
            text = "Salvar Contato"
            textSize = 18f
            setPadding(16, 16, 16, 16)
            setBackgroundColor(android.graphics.Color.LTGRAY)
            gravity = android.view.Gravity.CENTER
        }

        // Ação ao clicar no botão de salvar
        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val phone = phoneInput.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(name, phone)
                editor.apply()

                Toast.makeText(this, "Contato salvo!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }

        // Adicionando os componentes ao layout
        layout.addView(nameInput)
        layout.addView(phoneInput)
        layout.addView(saveButton)

        // Definindo o layout como conteúdo da atividade
        setContentView(layout)
    }
}
