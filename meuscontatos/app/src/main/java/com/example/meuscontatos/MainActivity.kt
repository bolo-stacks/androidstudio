package com.example.meuscontatos;

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.meuscontatos.MainActivity
import com.example.meuscontatos.AddContactActivity
import com.example.meuscontatos.DeleteContactsActivity
import com.example.meuscontatos.ViewContactsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criando o layout principal programaticamente
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.WHITE)
            setPadding(16, 16, 16, 16)
        }

        // Criando os TextViews para navegação
        val addContact = createTextView("Adicionar Contato")
        val viewContacts = createTextView("Visualizar Contatos")
        val deleteContacts = createTextView("Excluir Contatos")

        // Adicionando ações aos TextViews
        addContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        viewContacts.setOnClickListener {
            startActivity(Intent(this, ViewContactsActivity::class.java))
        }

        deleteContacts.setOnClickListener {
            startActivity(Intent(this, DeleteContactsActivity::class.java))
        }

        // Adicionando os TextViews ao layout principal
        mainLayout.addView(addContact)
        mainLayout.addView(viewContacts)
        mainLayout.addView(deleteContacts)

        // Definindo o layout principal como a visualização da Activity
        setContentView(mainLayout)
    }

    // Método auxiliar para criar TextViews
    private fun createTextView(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 20f
            setTextColor(Color.BLACK)
            setPadding(16, 16, 16, 16)
            gravity = Gravity.CENTER
        }
    }
}
