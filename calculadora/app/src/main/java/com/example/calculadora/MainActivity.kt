package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Usando View como áreas clicáveis (qualquer parte da tela que você quiser)
        val viewDolar = findViewById<View>(R.id.viewDolar)
        val viewIen = findViewById<View>(R.id.viewIen)
        val viewEuro = findViewById<View>(R.id.viewEuro)
        val viewLibra = findViewById<View>(R.id.viewLibra)

        viewDolar.setOnClickListener {
            startActivity(Intent(this, DolarActivity::class.java))
        }

        viewIen.setOnClickListener {
            startActivity(Intent(this, IenActivity::class.java))
        }

        viewEuro.setOnClickListener {
            startActivity(Intent(this, EuroActivity::class.java))
        }

        viewLibra.setOnClickListener {
            startActivity(Intent(this, LibraActivity::class.java))
        }
    }
}
