package com.example.eppi2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val buttonToggleTheme = findViewById<Button>(R.id.buttonToggleTheme) // Botão para alternar o tema

        // Navegação para outras atividades
        button1.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, Activity3::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, Activity4::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
        }

        // Alterna entre o modo claro e escuro ao clicar no botão
        buttonToggleTheme.setOnClickListener {
            toggleTheme()
        }
    }

    // Função para alternar entre os modos claro e escuro
    private fun toggleTheme() {
        // Verifica se o modo escuro está ativado
        if (isDarkModeEnabled()) {
            // Se estiver no modo escuro, altera para o modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Toast.makeText(this, "Modo Claro Ativado", Toast.LENGTH_SHORT).show()
        } else {
            // Se estiver no modo claro, altera para o modo escuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Toast.makeText(this, "Modo Escuro Ativado", Toast.LENGTH_SHORT).show()
        }
    }

    // Função para verificar se o modo escuro está ativado
    private fun isDarkModeEnabled(): Boolean {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        return currentMode == AppCompatDelegate.MODE_NIGHT_YES
    }
}
