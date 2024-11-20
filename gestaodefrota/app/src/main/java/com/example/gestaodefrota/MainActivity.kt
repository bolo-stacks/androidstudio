package com.example.gestaodefrota

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.gestaodefrota.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ArrayAdapter<Veiculo>
    private var pos : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val db = DBHelper(this)
        val listaVeiculos = db.veiculosSelectAll()

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaVeiculos
        )

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, i, _ ->
            binding.textId.setText("ID: ${ListaVeiculos[i].id}")
            binding.editMarca.setText(listaVeiculos[i].marca)
            binding.editModelosetText(listaVeiculos[i].modelo)
            pos = i
        }

        binding.buttonInsert.setOnClickListener {
            val marca = binding.editMarca.text.toString()
            val modelo = binding.editModelo.text.toString()

            val resultado = db.veiculoInsert(marca, modelo)
            if(resultado > 0) {
                Toast.makeText(applicationContext, "Sucesso! ID: $resultado")
            }
        }




    }
}