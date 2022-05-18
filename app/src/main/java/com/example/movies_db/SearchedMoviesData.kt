package com.example.movies_db

import com.squareup.moshi.Json
import java.io.Serializable

data class SearchedMoviesData(
    @Json(name="original_title") val title: String,
    @Json(name="popularity")  val popu: Float,
    @Json(name="id") val id: Int,
    @Json(name="poster_path") val poster_path: String?
) : Serializable
