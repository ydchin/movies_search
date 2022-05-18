package com.example.movies_db

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SearchMoviesAdapter(private val onSearchedMovieClick: (SearchedMoviesData) -> Unit) :
    RecyclerView.Adapter<SearchMoviesAdapter.SearchMoviesViewHolder>(){
    var searchMoviesList = listOf<SearchedMoviesData>()
    private var baseImageLink = "https://image.tmdb.org/t/p/w500"

    override fun getItemCount() = searchMoviesList.size

    fun updateSearchMovieList(newMovie: List<SearchedMoviesData>?){
        searchMoviesList = newMovie ?: listOf()
        Log.d("movie list",searchMoviesList.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesAdapter.SearchMoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_result, parent, false)
        return SearchMoviesViewHolder(itemView, onSearchedMovieClick)
    }

    override fun onBindViewHolder(holder: SearchMoviesAdapter.SearchMoviesViewHolder, position: Int) {
        holder.bind(searchMoviesList[position])
    }

    class SearchMoviesViewHolder(itemView: View, private val onClick: (SearchedMoviesData) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.original_title)
        private var rating: TextView = itemView.findViewById(R.id.moviePopularity)
        private var currentSearchedMovie: SearchedMoviesData? = null

        init {
            itemView.setOnClickListener{
                currentSearchedMovie?.let(onClick)

                //Store movie id in searchQuery object file (clickedID)
                searchQuery.clickedID = currentSearchedMovie?.id.toString()
                Log.d("current pressed", currentSearchedMovie?.id.toString())
            }
        }
        fun bind(searchedMoviesData: SearchedMoviesData) {
            currentSearchedMovie = searchedMoviesData
            title.text = searchedMoviesData.title
            rating.text = searchedMoviesData.popu.toInt().toString()
        }
    }
}