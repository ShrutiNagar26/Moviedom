package com.example.moviedom.data.model

import com.google.gson.annotations.SerializedName

data class Movies (
    @SerializedName("Search")
    var results:List<MovieDetails>,
    @SerializedName("totalResults")
    var totalResults: String
)