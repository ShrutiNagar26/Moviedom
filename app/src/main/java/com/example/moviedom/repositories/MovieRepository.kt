package com.example.moviedom.repositories

import com.example.moviedom.data.MovieApi
import com.example.moviedom.repositories.BaseRepository
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) : BaseRepository() {

    suspend fun getSearchedResults(searchedValue: String) = safeApiCall(
        call = {movieApi.searchMovies(searchedValue,"27f54060")},
        errorMessage = "Error occurred"
    )
}