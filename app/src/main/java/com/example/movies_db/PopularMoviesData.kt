package com.example.movies_db

import com.squareup.moshi.Json
import java.io.Serializable

// Grabs info from json after api call
// make sure name="" is something that is actually in the object
data class PopularMoviesData(
    @Json(name="name") val name: String,
    @Json(name="overview") val overview: String,
    @Json(name="first_air_date") val air_date: String,
    @Json(name="poster_path") val posterPath: String,
    @Json(name="popularity") val rating: Float
) : Serializable


