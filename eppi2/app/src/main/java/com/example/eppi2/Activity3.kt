package com.example.eppi2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar)

        // Referência ao EditText, Button e TextView
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val textViewNamesList = findViewById<TextView>(R.id.textViewNamesList)
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        // Acessa as SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Recupera a lista de contatos salva nas SharedPreferences
        var contactsSet = sharedPreferences.getStringSet("contactsList", mutableSetOf())

        // Atualiza a TextView com os contatos armazenados
        updateContactsList(textViewNamesList, contactsSet)

        // Configura o botão para salvar o nome e telefone na lista
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                // Cria o contato no formato "Nome - Telefone"
                val contact = "$name - $phone"

                // Adiciona o contato à lista
                contactsSet?.add(contact)

                // Salva a lista de contatos nas SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putStringSet("contactsList", contactsSet)
                editor.apply()

                // Atualiza a lista exibida na TextView
                updateContactsList(textViewNamesList, contactsSet)

                // Limpa os campos de texto após salvar
                editTextName.text.clear()
                editTextPhone.text.clear()

                // Mostra mensagem de sucesso
                Toast.makeText(this, "Contato adicionado à lista!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, insira nome e telefone.", Toast.LENGTH_SHORT).show()
            }
        }

        // Configura o botão "Voltar"
        buttonBack.setOnClickListener {
            finish() // Fecha a Activity atual e retorna à anterior
        }

        // Configura o clique na lista de contatos para editar
        textViewNamesList.setOnClickListener {
            val contactsArray = contactsSet?.toTypedArray() ?: arrayOf()

            // Cria o diálogo de seleção de contato
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Selecione um contato para editar")
                .setItems(contactsArray) { dialog, which ->
                    val selectedContact = contactsArray[which]
                    val contactParts = selectedContact.split(" - ")
                    val selectedName = contactParts[0]
                    val selectedPhone = contactParts[1]

                    // Coloca o nome e o telefone no EditText
                    editTextName.setText(selectedName)
                    editTextPhone.setText(selectedPhone)

                    // Altera o contato quando pressionar o botão salvar
                    buttonSave.setOnClickListener {
                        val newName = editTextName.text.toString()
                        val newPhone = editTextPhone.text.toString()

                        if (newName.isNotEmpty() && newPhone.isNotEmpty()) {
                            // Remove o contato antigo e adiciona o novo
                            contactsSet?.remove(selectedContact)
                            val newContact = "$newName - $newPhone"
                            contactsSet?.add(newContact)

                            // Salva a lista de contatos nas SharedPreferences
                            val editor = sharedPreferences.edit()
                            editor.putStringSet("contactsList", contactsSet)
                            editor.apply()

                            // Atualiza a lista exibida na TextView
                            updateContactsList(textViewNamesList, contactsSet)

                            // Limpa os campos de texto após salvar
                            editTextName.text.clear()
                            editTextPhone.text.clear()

                            // Mostra mensagem de sucesso
                            Toast.makeText(this, "Contato alterado com sucesso!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Por favor, insira um nome e telefone válidos.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .show()
        }
    }

    // Função para atualizar a lista de contatos na TextView
    private fun updateContactsList(textView: TextView, contactsSet: Set<String>?) {
        val contactsList = contactsSet?.joinToString("\n") ?: "Nenhum contato adicionado"
        textView.text = contactsList
    }
}
