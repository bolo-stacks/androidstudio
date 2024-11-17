package com.example.resultadosdapartida

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.resultadosdapartida.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        binding.textPal.setOnClickListener {
            var gols = binding.textPlacarPal.text.toString().toInt()
            binding.textPlacarPal.setText("${gols + 1}")
        }

        binding.textInt.setOnClickListener {
            var gols = binding.textPlacarInt.text.toString().toInt()
            binding.textPlacarInt.setText("${gols + 1}")
        }
    }
}