package com.example.meucontatos3

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listarContatosTextView = findViewById<TextView>(R.id.listarContatos)
        val adicionarContatoTextView = findViewById<TextView>(R.id.adicionarContato)
        val editarContatoTextView = findViewById<TextView>(R.id.editarContato)
        val apagarContatosTextView = findViewById<TextView>(R.id.apagarContatos)
        val exportarContatosTextView = findViewById<TextView>(R.id.exportarContatos)

        listarContatosTextView.setOnClickListener {
            val intent = Intent(this, ListarContatosActivity::class.java)
            startActivity(intent)
        }

        adicionarContatoTextView.setOnClickListener {
            val intent = Intent(this, AdicionarContatoActivity::class.java)
            startActivity(intent)
        }

        editarContatoTextView.setOnClickListener {
            val intent = Intent(this, EditarContatoActivity::class.java)
            startActivity(intent)
        }

        apagarContatosTextView.setOnClickListener {
            SharedPreferencesHelper.limparContatos(this)
        }

        exportarContatosTextView.setOnClickListener {
            exportarContatos()
        }
    }

    private fun exportarContatos() {
        // Verificando permissões para gravar no armazenamento externo
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            // Caso a permissão tenha sido concedida, exportar os contatos
            val contatos = SharedPreferencesHelper.getContatos(this)
            val texto = contatos.joinToString("\n") { "${it.nome} | ${it.telefone} | ${it.apelido}" }

            val file = File(getExternalFilesDir(null), "contatos.txt")
            try {
                val fos = FileOutputStream(file)
                fos.write(texto.toByteArray())
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Lidar com a resposta da solicitação de permissão
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            exportarContatos()
        }
    }
}
