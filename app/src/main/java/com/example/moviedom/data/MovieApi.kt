package com.example.moviedom.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

@GET("/")
suspend fun searchMovies(@Query("s")search:String, @Query("apikey")apiKey:String):Response<Movies>
}