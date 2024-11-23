package com.example.meucontatos2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetailsActivity : AppCompatActivity() {

    private lateinit var textNome: TextView
    private lateinit var textTelefone: TextView
    private lateinit var textEmail: TextView
    private lateinit var textEndereco: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        textNome = findViewById(R.id.textNome)
        textTelefone = findViewById(R.id.textTelefone)
        textEmail = findViewById(R.id.textEmail)
        textEndereco = findViewById(R.id.textEndereco)

        val contato: Contato = intent.getSerializableExtra("contato") as Contato

        textNome.text = contato.nome
        textTelefone.text = contato.telefone
        textEmail.text = contato.email
        textEndereco.text = contato.endereco
    }

    fun exportarParaWhatsApp() {
        val contatoTexto = "Nome: ${textNome.text}\nTelefone: ${textTelefone.text}"
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, contatoTexto)
            type = "text/plain"
            setPackage("com.whatsapp")
        }
        startActivity(intent)
    }

    fun exportarParaEmail() {
        val contatoTexto = "Nome: ${textNome.text}\nTelefone: ${textTelefone.text}"
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("email@exemplo.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Contato")
            putExtra(Intent.EXTRA_TEXT, contatoTexto)
            type = "message/rfc822"
        }
        startActivity(Intent.createChooser(intent, "Enviar por E-mail"))
    }
}
