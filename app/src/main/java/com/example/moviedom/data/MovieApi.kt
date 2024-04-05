package com.example.moviedom.data

import com.example.moviedom.other.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

@GET("/")
suspend fun searchMovies(@Query("s")search:String, @Query("apikey")apiKey:String = Constants.API_KEY, @Query("page")page:Int):Movies
}