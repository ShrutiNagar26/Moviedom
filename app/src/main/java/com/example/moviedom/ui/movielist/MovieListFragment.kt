package com.example.moviedom.ui.movielist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.moviedom.R
import com.example.moviedom.data.MovieDetails
import com.example.moviedom.databinding.FragmentMovieListBinding
import com.example.moviedom.ui.adapter.MovieItemRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding:FragmentMovieListBinding
    private var movieListAdapter: MovieItemRecyclerAdapter? = null
    private val movieViewModel : MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        setMovieListAdapter()
        binding.searchMovies.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?){
                searchMovie(s.toString())
            }

        })

        return binding.root
    }

    private fun searchMovie(searchedValue:String){
        movieViewModel.getSearchedMovies(searchedValue).observe(viewLifecycleOwner, Observer {
                movieListAdapter!!.submitData(viewLifecycleOwner.lifecycle,it)
        })
    }

    private fun setMovieListAdapter() {
        movieListAdapter = MovieItemRecyclerAdapter()
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.listMovies.layoutManager = layoutManager
        binding.listMovies.adapter = movieListAdapter

    }
}