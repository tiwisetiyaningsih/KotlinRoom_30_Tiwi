package com.example.kotlin_room_30_tiwi.room

import android.graphics.Movie
import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    suspend fun  addMovie(movie: MovieAnime)

    @Update
    suspend fun  updateMovie(movie: MovieAnime)

    @Delete
    suspend fun  deleteMovie(movie: MovieAnime)

    @Query ("SELECT * FROM movieanime")
    suspend fun getMovies():List<MovieAnime>

    @Query ("SELECT * FROM movieanime WHERE id=:movie_id")
    suspend fun getMovie(movie_id:Int):List<MovieAnime>
}