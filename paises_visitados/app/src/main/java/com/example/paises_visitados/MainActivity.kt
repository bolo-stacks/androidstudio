package com.example.paises_visitados

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.paises_visitados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val sharedPreferences = this.getSharedPreferences("dados", Context.MODE_PRIVATE)
        var conteudo = sharedPreferences.getString("paises_notas", "").toString()
        binding.textDados.setText(conteudo)

        binding.buttonIncluir.setOnClickListener {
            val pais = binding.editPais.text.toString().trim()
            val nota = binding.editNota.text.toString().trim()

            if(!pais.isEmpty() && !nota.isEmpty()) {
                val editor = sharedPreferences.edit()
                if(!conteudo.isEmpty())
                    conteudo += "\n"
                conteudo += pais + " - " + nota
                editor.putString("paises_notas", conteudo)
                editor.apply()
                binding.textDados.setText(conteudo)
                binding.editPais.setText("")
                binding.editNota.setText("")
            }
        }
    }
}