package com.example.moviedom.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedom.data.Movies
import com.example.moviedom.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository):ViewModel() {


    private val searchedMovieResponse: MutableLiveData<Movies> = MutableLiveData()
     val searchedMovieResponseList: LiveData<Movies>
        get() = searchedMovieResponse
    fun getSearchedMovies(searchedValue: String){
        viewModelScope.launch(Dispatchers.IO){
            searchedMovieResponse.postValue(repository.getSearchedResults(searchedValue))
        }
    }
}