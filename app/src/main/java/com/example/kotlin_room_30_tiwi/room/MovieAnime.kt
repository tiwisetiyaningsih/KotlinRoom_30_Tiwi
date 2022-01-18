package com.example.kotlin_room_30_tiwi.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class MovieAnime (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
     val title: String,
     val desc: String
         )
