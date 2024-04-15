package com.example.moviedom.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(

    @SerializedName("Title")
    var title:String = "",
    @SerializedName("Poster")
    var poster:String = "",
    @SerializedName("Year")
    var year:String = "",
    @SerializedName("imdbID")
    var imdbID:String = ""
)
