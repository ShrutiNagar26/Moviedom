package com.example.moviedom.data

import com.google.gson.annotations.SerializedName

data class Movies (
    @SerializedName("Search")
    var results:List<MovieDetails>
)