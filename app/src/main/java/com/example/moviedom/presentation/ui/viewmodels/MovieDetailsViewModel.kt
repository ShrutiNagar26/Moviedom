package com.example.moviedom.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedom.data.model.MovieItemDetails
import com.example.moviedom.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var movieItemDetails:MutableLiveData<MovieItemDetails?> = MutableLiveData()
init {
    if(savedStateHandle != null){
        val imdbId = savedStateHandle?.get<String>("movieId")
        val posterValue = savedStateHandle?.get<String>("poster")
        getMovieDetails(imdbId!!, posterValue!!)
    }
}
     val movieItem: MutableLiveData<MovieItemDetails?>
        get() = movieItemDetails

     private fun getMovieDetails(id:String, poster:String){
        viewModelScope.launch {
            val result = repository.getMovieDetails(id)
            if (result != null && result.moviePoster == null) {
                result.moviePoster = poster
            }
            movieItemDetails.postValue(result)
        }
    }
}