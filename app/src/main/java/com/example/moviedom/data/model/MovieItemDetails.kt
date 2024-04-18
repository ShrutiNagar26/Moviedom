package com.example.moviedom.data.model

import com.google.gson.annotations.SerializedName

data class MovieItemDetails(
    @SerializedName("Title")
    var title:String,
    @SerializedName("Released")
    var releaseDate:String,
    @SerializedName("Director")
    var director:String,
    @SerializedName("Plot")
    var plot:String,
    @SerializedName("imdbRating")
    var imdbRating:String,
    @SerializedName("Poster")
    var moviePoster:String
)
