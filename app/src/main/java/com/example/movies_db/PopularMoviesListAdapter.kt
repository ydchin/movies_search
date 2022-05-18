package com.example.movies_db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Class used for handling for popular movies
class PopularMoviesListAdapter(private val onPopularMovieClick: (PopularMoviesData) -> Unit)
    : RecyclerView.Adapter<PopularMoviesListAdapter.PopularMoviesViewHolder>() {
    var popularMoviesList = listOf<PopularMoviesData>()

    override fun getItemCount() = popularMoviesList.size

    fun updatePopularMovieList(newMovie: List<PopularMoviesData>?){
        popularMoviesList = newMovie ?: listOf()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_list_item, parent, false)
        return PopularMoviesViewHolder(itemView, onPopularMovieClick)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(popularMoviesList[position])
    }

    class PopularMoviesViewHolder(itemView: View, val onClick: (PopularMoviesData) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private var placeTV: TextView = itemView.findViewById(R.id.place)
        private var nameTV: TextView = itemView.findViewById(R.id.tv_name)
        private var ratingTV: TextView = itemView.findViewById(R.id.rating)
        private var currentPopularMovie: PopularMoviesData? = null

        init {
            itemView.setOnClickListener{
                currentPopularMovie?.let(onClick)
            }
        }

        fun bind(popularMoviesData: PopularMoviesData) {
            currentPopularMovie = popularMoviesData

            // Rankings ( +1 because it starts at 0 :) )
            placeTV.text = (adapterPosition + 1).toString()

            // Movie Titles
            nameTV.text = popularMoviesData.name

            // Ratings (Float -> Int conversion for better visuals)
            ratingTV.text = popularMoviesData.rating.toInt().toString()

        }

    }
}