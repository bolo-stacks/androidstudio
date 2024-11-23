package com.example.meucontatos2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editTelefone: EditText
    private lateinit var editEmail: EditText
    private lateinit var editEndereco: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        editNome = findViewById(R.id.editNome)
        editTelefone = findViewById(R.id.editTelefone)
        editEmail = findViewById(R.id.editEmail)
        editEndereco = findViewById(R.id.editEndereco)
    }

    fun salvarContato() {
        val nome = editNome.text.toString()
        val telefone = editTelefone.text.toString()
        val email = editEmail.text.toString()
        val endereco = editEndereco.text.toString()

        val sharedPreferences: SharedPreferences = getSharedPreferences("MeuContatos", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("contato_nome", nome)
        editor.putString("contato_telefone", telefone)
        editor.putString("contato_email", email)
        editor.putString("contato_endereco", endereco)
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
