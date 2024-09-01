package com.example.investimento

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investimento.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        binding.buttonCalcula.setOnClickListener {
            val capital = binding.textValor.text.toString().toDouble()
            val taxa = binding.textTaxa  .text.toString().toDouble()
            val periodo = binding.textPeriodo.text.toString().toDouble()

            // m = c * ( 1 + i) ^ t
            var resultado = String.format("%.2f", capital * (1 + taxa).pow(periodo));
            binding.textResultado.setText("R$ $resultado");




        }

    }
}



