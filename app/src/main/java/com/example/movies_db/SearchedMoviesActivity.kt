package com.example.movies_db

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso

class SearchedMoviesActivity : AppCompatActivity() {
    private val apiBaseUrl = "https://api.themoviedb.org/3"
    private val tag = "MainActivity"
    private lateinit var searchResultsListRV: RecyclerView
    private lateinit var requestQueue: RequestQueue
    private val searchAdapter = SearchMoviesAdapter(::onSearchMovieClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_results)

        Log.d("curr quer", searchQuery.querySearch.toString())

        requestQueue = Volley.newRequestQueue(this)

        searchResultsListRV = findViewById(R.id.rv_search_results)
        searchResultsListRV.layoutManager = LinearLayoutManager(this)
        searchResultsListRV.setHasFixedSize(true)

        searchResultsListRV.adapter = searchAdapter

        doShowSearch(searchQuery.querySearch.toString())

        searchResultsListRV.scrollToPosition(0)

    }

    // Complete 2nd API call
    fun doShowSearch(q: String) {
        val url = "$apiBaseUrl/search/movie?api_key=e4e11a88c3668c0d61205b506e614a54&query=$q"

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<SearchedMoviesResults> =
            moshi.adapter(SearchedMoviesResults::class.java)
        val req = StringRequest(
            Request.Method.GET,
            url,
            {
                var results = jsonAdapter.fromJson(it)
                Log.d("yo2", results.toString())
                searchAdapter.updateSearchMovieList(results?.results)
            },
            {
                Log.d("sup2", "Error fetching from $url: ${it.message}")
            }
        )
        requestQueue.add(req)
    }

    // Function used for when a popular movie is clicked on
    private fun onSearchMovieClick(movie: SearchedMoviesData) {
        val intent = Intent(this, SearchedMoviesDetails::class.java).apply {
            putExtra(EXTRA_SEARCH_MOVIE, movie)
        }
        startActivity(intent)
    }

    private data class SearchedMoviesResults (
        val results: List<SearchedMoviesData>
    )
}