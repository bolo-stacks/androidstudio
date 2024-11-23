package com.example.meucontatos2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ExportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)
    }

    fun exportarParaWhatsApp() {
        val contatoTexto = "Nome: João\nTelefone: 123456789"
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, contatoTexto)
            type = "text/plain"
            setPackage("com.whatsapp")
        }
        startActivity(intent)
    }

    fun exportarParaEmail() {
        val contatoTexto = "Nome: João\nTelefone: 123456789"
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("email@exemplo.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Contato")
            putExtra(Intent.EXTRA_TEXT, contatoTexto)
            type = "message/rfc822"
        }
        startActivity(Intent.createChooser(intent, "Enviar por E-mail"))
    }
}
