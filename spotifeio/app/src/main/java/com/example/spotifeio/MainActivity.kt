package com.example.spotifeio

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spotifeio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mediaPlayer : MediaPlayer
    private var playing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBaitaca.setOnClickListener {
            if (playing) mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.baitaca_musica)
            mediaPlayer.start()
            playing = true
        }

        binding.imgGaucho.setOnClickListener {
            if (playing) mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.gaucho_musica)
            mediaPlayer.start()
            playing = true
        }

        binding.imgMano.setOnClickListener {
            if (playing) mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.mano_musica)
            mediaPlayer.start()
            playing = true
        }
    }
}