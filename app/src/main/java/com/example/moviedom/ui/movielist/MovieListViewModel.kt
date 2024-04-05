package com.example.moviedom.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviedom.data.MovieDetails
import com.example.moviedom.data.Movies
import com.example.moviedom.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository):ViewModel() {

    fun getSearchedMovies(searchedValue: String): LiveData<PagingData<MovieDetails>> {
        return repository.getSearchedResults(searchedValue)
            .cachedIn(viewModelScope)
    }
}