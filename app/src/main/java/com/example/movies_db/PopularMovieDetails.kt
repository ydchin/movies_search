package com.example.movies_db

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

const val EXTRA_POPULAR_MOVIE = "PopularMoviesData"

class PopularMovieDetails : AppCompatActivity() {
    private var movie: PopularMoviesData? = null
    private var baseImageLink = "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movie_detail)
        if (intent != null && intent.hasExtra(EXTRA_POPULAR_MOVIE)) {
            movie = intent.getSerializableExtra(EXTRA_POPULAR_MOVIE) as PopularMoviesData

            // Used to display image in popular movies details
            Picasso.with(this).load(baseImageLink + movie!!.posterPath).into(findViewById<ImageView>(R.id.detail_image))
            findViewById<TextView>(R.id.detail_title).text = movie!!.name

            // Some movies don't have a overview, so simple error handling.
            if(movie!!.overview != "") {
                findViewById<TextView>(R.id.detail_overview).text = "Overview:" + movie!!.overview
            } else {
                findViewById<TextView>(R.id.detail_overview).text = "Overview: Not Available"
            }

            findViewById<TextView>(R.id.detail_release).text = "First Aired: " + movie!!.air_date
        }
    }
}