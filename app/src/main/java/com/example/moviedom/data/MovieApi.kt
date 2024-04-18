package com.example.moviedom.data

import com.example.moviedom.data.model.MovieDetails
import com.example.moviedom.data.model.MovieItemDetails
import com.example.moviedom.data.model.Movies
import com.example.moviedom.presentation.other.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("s") search: String,
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int
    ): Movies

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") search: String,
        @Query("apikey") apiKey: String = Constants.API_KEY
    ): Response<MovieItemDetails>
}