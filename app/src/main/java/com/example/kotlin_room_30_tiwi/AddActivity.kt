package com.example.kotlin_room_30_tiwi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kotlin_room_30_tiwi.room.Constant
import com.example.kotlin_room_30_tiwi.room.MovieAnime
import com.example.kotlin_room_30_tiwi.room.MovieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy { MovieDb(this) }
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupView()
        setupListener()
        movieId = intent.getIntExtra("intent_id", 0)
        Toast.makeText(this, movieId.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                supportActionBar!!.title = "CREATE NEW ANIME MOVIE"
                btn_update.visibility = View.GONE
                text_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                supportActionBar!!.title = "READ ANIME MOVIE"
                btn_save.visibility = View.GONE
                btn_update.visibility = View.GONE
                text_update.visibility = View.GONE
                text_add.visibility = View.GONE
                getMovie()
            }
            Constant.TYPE_UPDATE -> {
                supportActionBar!!.title = "UPDATE ANIME MOVIE"
                btn_save.visibility = View.GONE
                text_add.visibility = View.GONE
                getMovie()
            }
        }
    }

    fun setupListener(){
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().addMovie(
                    MovieAnime(0,et_tittle.text.toString(),
                    et_description.text.toString())
                )

                finish()
            }
        }
        btn_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().updateMovie(
                    MovieAnime(movieId,et_tittle.text.toString(),
                        et_description.text.toString())
                )

                finish()
            }
        }
    }

    fun getMovie(){
        movieId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getMovie(movieId)[0]
            et_tittle.setText(movies.title)
            et_description.setText(movies.desc)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
