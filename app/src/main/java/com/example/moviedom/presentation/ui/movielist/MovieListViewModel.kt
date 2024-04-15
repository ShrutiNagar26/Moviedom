package com.example.moviedom.presentation.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviedom.data.model.MovieDetails
import com.example.moviedom.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository):ViewModel() {

    fun getSearchedMovies(searchedValue: String): LiveData<PagingData<MovieDetails>> {
        return repository.getSearchedResults(searchedValue)
            .cachedIn(viewModelScope)
    }
}