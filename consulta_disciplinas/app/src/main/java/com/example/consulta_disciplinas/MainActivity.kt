package com.example.consulta_disciplinas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.consulta_disciplinas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val disciplinas = ArrayList<String>()
        disciplinas.add("Portugues")
        disciplinas.add("História")
        disciplinas.add("Consciência")
        disciplinas.add("Matemática")
        disciplinas.add("Filosofia")

        val professores = ArrayList<String>()
        professores.add("José")
        professores.add("João")
        professores.add("Jacob")
        professores.add("Jeff")
        professores.add("Jim")

        val horarios = ArrayList<String>()
        horarios.add("Segunda")
        horarios.add("Terça")
        horarios.add("Quarta")
        horarios.add("Quinta")
        horarios.add("Sexta")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, disciplinas)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Professor: ${professores.get(i)}; Horario: ${horarios.get(i)}.", Toast.LENGTH_SHORT).show()
        }
    }
}

