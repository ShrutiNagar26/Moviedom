package com.example.moviedom.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedom.data.Movies
import com.example.moviedom.ui.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository):ViewModel() {


    private val searchedMovieResponse: MutableLiveData<Movies> = MutableLiveData()

    fun getSearchedMovies(searchedValue: String): MutableLiveData<Movies> {
        viewModelScope.launch(Dispatchers.IO){
            searchedMovieResponse.postValue(repository.getSearchedResults(searchedValue))
        }
        return searchedMovieResponse
    }
}