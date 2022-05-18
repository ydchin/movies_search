package com.example.movies_db

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso

const val EXTRA_SEARCH_MOVIE = "PopularMoviesData"

class SearchedMoviesDetails : AppCompatActivity() {

    private var movie: SearchedMoviesData? = null
    private var baseImageLink = "https://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movie_detail)



        // DO WORK HERE!!

//        if (intent != null && intent.hasExtra(EXTRA_POPULAR_MOVIE)) {
//            movie = intent.getSerializableExtra(EXTRA_SEARCH_MOVIE) as SearchedMoviesData
//
//            // Used to display image in popular movies details
//            Picasso.with(this).load(baseImageLink + movie!!.poster_path).into(findViewById<ImageView>(R.id.detail_image))
//        }


    }


}