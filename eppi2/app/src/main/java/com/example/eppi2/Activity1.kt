package com.example.eppi2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)

        // Referência ao EditText para nome, telefone, Button e TextView
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonExport = findViewById<Button>(R.id.buttonExport) // Botão para exportar
        val textViewNamesList = findViewById<TextView>(R.id.textViewNamesList)
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        // Acessa as SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Recupera a lista de contatos salvos nas SharedPreferences
        val savedData = sharedPreferences.getStringSet("contactsList", mutableSetOf())

        // Atualiza a TextView com os contatos armazenados
        updateContactsList(textViewNamesList, savedData)

        // Configura o botão para salvar o nome e telefone
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                // Adiciona o nome e telefone à lista de contatos
                val contact = "$name - $phone"
                savedData?.add(contact)

                // Salva a lista de contatos nas SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putStringSet("contactsList", savedData)
                editor.apply()

                // Atualiza a lista exibida na TextView
                updateContactsList(textViewNamesList, savedData)

                // Limpa os campos de texto após salvar
                editTextName.text.clear()
                editTextPhone.text.clear()

                // Mostra mensagem de sucesso
                Toast.makeText(this, "Contato adicionado!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, insira nome e telefone.", Toast.LENGTH_SHORT).show()
            }
        }

        // Configura o botão "Voltar"
        buttonBack.setOnClickListener {
            finish() // Fecha a Activity atual e retorna à anterior
        }

        // Configura o botão "Exportar"
        buttonExport.setOnClickListener {
            exportContactsToFile(savedData)
        }
    }

    // Função para atualizar a lista de contatos na TextView
    private fun updateContactsList(textView: TextView, savedData: Set<String>?) {
        val contactsList = savedData?.joinToString("\n") ?: "Nenhum contato adicionado"
        textView.text = contactsList
    }

    // Função para exportar os contatos para um arquivo de texto
    private fun exportContactsToFile(savedData: Set<String>?) {
        if (savedData.isNullOrEmpty()) {
            Toast.makeText(this, "Não há contatos para exportar.", Toast.LENGTH_SHORT).show()
            return
        }

        // Cria um arquivo de texto na pasta de arquivos do aplicativo
        val fileName = "contacts_list.txt"
        val file = File(filesDir, fileName)

        try {
            val fileOutputStream = FileOutputStream(file)
            savedData.forEach {
                fileOutputStream.write((it + "\n").toByteArray()) // Escreve cada contato no arquivo
            }
            fileOutputStream.close()

            // Mostra uma mensagem informando que o arquivo foi criado com sucesso
            Toast.makeText(this, "Contatos exportados para o arquivo ${file.absolutePath}", Toast.LENGTH_LONG).show()

        } catch (e: IOException) {
            // Caso ocorra algum erro ao criar o arquivo
            Toast.makeText(this, "Erro ao exportar contatos.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
