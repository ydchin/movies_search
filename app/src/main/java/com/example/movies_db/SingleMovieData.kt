package com.example.movies_db

import com.squareup.moshi.Json
import java.io.Serializable

data class SingleMovieData(
    @Json(name="original_title") val title: String,
    @Json(name="poster_path") val pathToPoster: String
) : Serializable
