package com.example.meucontatos2

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextTelefone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextEndereco: EditText
    private lateinit var textViewSalvar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        // Inicializando os campos de entrada
        editTextNome = findViewById(R.id.editTextNome)
        editTextTelefone = findViewById(R.id.editTextTelefone)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextEndereco = findViewById(R.id.editTextEndereco)
        textViewSalvar = findViewById(R.id.textViewSalvar)

        // Configurando o clique do TextView para salvar o contato
        textViewSalvar.setOnClickListener {
            salvarContato()
        }
    }

    private fun salvarContato() {
        val nome = editTextNome.text.toString()
        val telefone = editTextTelefone.text.toString()
        val email = editTextEmail.text.toString()
        val endereco = editTextEndereco.text.toString()

        if (nome.isNotEmpty() && telefone.isNotEmpty()) {
            val sharedPreferences = getSharedPreferences("contacts", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            // Usando um identificador único para salvar múltiplos contatos
            val contatoId = System.currentTimeMillis().toString()

            editor.putString("$contatoId_nome", nome)
            editor.putString("$contatoId_telefone", telefone)
            editor.putString("$contatoId_email", email)
            editor.putString("$contatoId_endereco", endereco)
            editor.apply()

            Toast.makeText(this, "Contato salvo com sucesso!", Toast.LENGTH_SHORT).show()
            finish() // Voltar para a tela anterior
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show()
        }
    }
}
