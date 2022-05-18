package com.example.movies_db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {
    private val apiBaseUrl = "https://api.themoviedb.org/3"
    private val tag = "MainActivity"

    private val popularAdapter = PopularMoviesListAdapter(::onPopularMovieClick)

    private lateinit var requestQueue: RequestQueue

    private lateinit var popularMoviesResultsListRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)

        val searchBoxET: EditText = findViewById(R.id.et_search_box)
        val searchBtn: Button = findViewById(R.id.btn_search)

        popularMoviesResultsListRV = findViewById(R.id.rv_popular_movie_list)
        popularMoviesResultsListRV.layoutManager = LinearLayoutManager(this)
        popularMoviesResultsListRV.setHasFixedSize(true)

        popularMoviesResultsListRV.adapter = popularAdapter

        // Used to display all the popular movies
        doPopularMoviesSearch()
        popularMoviesResultsListRV.scrollToPosition(0)


        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            Log.d("searching... ",query)
            if(!TextUtils.isEmpty(query)) {

                // If search box hits submit, store query into object struct (searchQuery)
                searchQuery.querySearch = query
                val intent = Intent(this, SearchedMoviesActivity::class.java)
                startActivity(intent)

            }
        }
    }


    // Function used for popular movies api search
    fun doPopularMoviesSearch(q: String = "e4e11a88c3668c0d61205b506e614a54") {
        val url = "$apiBaseUrl/tv/popular?api_key=e4e11a88c3668c0d61205b506e614a54&language=en-US&page=1"

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<PopularMoviesResults> =
            moshi.adapter(PopularMoviesResults::class.java)
        val req = StringRequest(
            Request.Method.GET,
            url,
            {
                var results = jsonAdapter.fromJson(it)
                Log.d("yo", results.toString())
                popularAdapter.updatePopularMovieList(results?.results)
            },
            {
                Log.d("sup", "Error fetching from $url: ${it.message}")
            }
        )
        requestQueue.add(req)
    }

    // Function used for when a popular movie is clicked on
    private fun onPopularMovieClick(movie: PopularMoviesData) {
        val intent = Intent(this, PopularMovieDetails::class.java).apply {
            putExtra(EXTRA_POPULAR_MOVIE, movie)
        }
        startActivity(intent)
    }

    // Import data class from popular movie results
    private data class PopularMoviesResults (
        val results: List<PopularMoviesData>
        )

}