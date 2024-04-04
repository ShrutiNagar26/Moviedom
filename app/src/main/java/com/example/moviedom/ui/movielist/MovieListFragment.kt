package com.example.moviedom.ui.movielist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.moviedom.R
import com.example.moviedom.data.MovieDetails
import com.example.moviedom.databinding.FragmentMovieListListBinding
import com.example.moviedom.ui.adapter.MovieRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var movieItemList: MutableList<MovieDetails> = ArrayList()
    private lateinit var binding:FragmentMovieListListBinding
    private var movieListAdapter: MovieRecyclerViewAdapter? = null
    private val movieViewModel : MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list_list, container, false)

        binding = FragmentMovieListListBinding.inflate(layoutInflater)
        setMovieListAdapter()
        getUpdatedMovieList()
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

    private fun getUpdatedMovieList() {
        movieViewModel.searchedMovieResponseList.observe(viewLifecycleOwner, Observer {
            if(it != null && it.results != null){
                movieItemList = it.results as MutableList<MovieDetails>
                movieListAdapter!!.setMovieItemList(movieItemList)
            }
            else{
                movieListAdapter!!.setMovieItemList(mutableListOf())
            }
        })
    }

    private fun searchMovie(searchedValue:String){
        movieViewModel.getSearchedMovies(searchedValue)
    }

    private fun setMovieListAdapter() {

        movieListAdapter = MovieRecyclerViewAdapter(movieItemList)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.list.layoutManager = layoutManager
        binding.list.adapter = movieListAdapter

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}