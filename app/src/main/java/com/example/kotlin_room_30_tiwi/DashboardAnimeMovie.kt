package com.example.kotlin_room_30_tiwi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.kotlin_room_30_tiwi.room.MovieAnime
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_dashboard_anime_movie.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardAnimeMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_anime_movie)
        val intentCardView: CardView = findViewById(R.id.genre_action)
        intentCardView.setOnClickListener { viewAddMovie() }
    }
    private fun viewAddMovie() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}