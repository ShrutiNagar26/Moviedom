package com.example.moviedom.presentation.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.moviedom.data.model.MovieItemDetails
import com.example.moviedom.databinding.FragmentMovieDetailsBinding
import com.example.moviedom.presentation.ui.MainActivity
import com.example.moviedom.presentation.ui.viewmodels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var detailsBinding:FragmentMovieDetailsBinding
    private var posterValue:String = ""
    private var imdbId: String = ""
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        viewModel.movieItem.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                (activity as MainActivity).setToolBarTitle(it.title)
                updateDetailsUI(it)
            }
        })

        return detailsBinding.root
    }


    private fun updateDetailsUI(movieDetails: MovieItemDetails) {
        detailsBinding.titleMovie.text = movieDetails.title
        detailsBinding.plot.text = movieDetails.plot
        detailsBinding.imageMovies.load(movieDetails.moviePoster)
        detailsBinding.ratingProgress.progress = (movieDetails.imdbRating.toFloat() * 10.0).toInt()
        detailsBinding.ratingText.text = movieDetails.imdbRating

    }


}