package com.example.moviedom.data

import com.google.gson.annotations.SerializedName

data class MovieDetails(

    @SerializedName("Title")
    var title:String = "",
    @SerializedName("Poster")
    var poster:String = "",
    @SerializedName("Year")
    var year:String = ""
)
