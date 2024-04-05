package com.example.moviedom.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moviedom.data.MovieApi
import com.example.moviedom.data.MovieDetails
import com.example.moviedom.data.MoviePagingSource
import com.example.moviedom.repositories.BaseRepository
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi){

    fun getSearchedResults(searchedValue: String): LiveData<PagingData<MovieDetails>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = { MoviePagingSource(movieApi,searchedValue)}
    ).liveData
}