package com.example.milhakm

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.milhakm.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        binding.buttonConverter.setOnClickListener {
            val milhas = binding.editMilhas.text.toString().toDouble()
            val resultado = milhas * 1.61
            binding.textKm.setText("Valor em Km: $resultado")
        }

    }
}