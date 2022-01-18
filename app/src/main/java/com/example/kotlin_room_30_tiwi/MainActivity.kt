package com.example.kotlin_room_30_tiwi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_room_30_tiwi.room.Constant
import com.example.kotlin_room_30_tiwi.room.MovieAnime
import com.example.kotlin_room_30_tiwi.room.MovieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { MovieDb(this) }
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupRecyclerView()

        Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()

    }

    override fun onStart() {
        super.onStart()
        loadMovie()
    }

    fun loadMovie(){
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getMovies()
            Log.d("MainActivity","dbresponse: $movies")
            withContext(Dispatchers.Main){
                movieAdapter.setData(movies)
            }
        }
    }

    fun setupListener(){
        add_movie.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(movieId:Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddActivity::class.java)
                .putExtra("intent_id", movieId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun setupRecyclerView(){
        movieAdapter = MovieAdapter(arrayListOf(),object : MovieAdapter.OnAdapterListener{
            override fun onClick(movie: MovieAnime) {
//                Toast.makeText(applicationContext, movie.title, Toast.LENGTH_SHORT).show()
                // read detail movie
                intentEdit(movie.id,Constant.TYPE_READ)
            }

            // update movie
            override fun onUpdate(movie: MovieAnime) {
                intentEdit(movie.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(movie: MovieAnime) {
                deleteDialog(movie)
            }

        })
        rv_movie.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
        }
    }

    private fun deleteDialog(movie: MovieAnime) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus ${movie.title}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.movieDao().deleteMovie(movie)
                    loadMovie()
                }
            }
        }
        alertDialog.show()
    }
}