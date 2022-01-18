package com.example.kotlin_room_30_tiwi

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_room_30_tiwi.room.MovieAnime
import kotlinx.android.synthetic.main.list_movie.view.*
import java.util.*

class MovieAdapter(private val movies: ArrayList<MovieAnime>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie  = movies[position]
        holder.view.text_title.text = movie.title
        holder.view.text_title.setOnClickListener {
            listener.onClick(movie)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(movie)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(movie)
        }
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<MovieAnime>){
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(movie : MovieAnime)
        fun onUpdate(movie : MovieAnime)
        fun onDelete(movie : MovieAnime)
    }
}