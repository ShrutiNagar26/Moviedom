package com.example.moviedom.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviedom.R
import com.example.moviedom.data.MovieDetails
import com.example.moviedom.databinding.FragmentMovieListItemsBinding

class MovieItemRecyclerAdapter:
    PagingDataAdapter<MovieDetails, MovieItemRecyclerAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieDetails>() {
            override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean =
                oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean =
                oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
//    fun setMovieItemList(scanList: List<MovieDetails>) {
//        values = scanList as MutableList<MovieDetails>
//        notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.movieTitle.text = item.title
            holder.releaseYear.text = item.year

            holder.posterImage.load(item.poster) {
                crossfade(true)
                placeholder(R.drawable.no_image)
            }
        }

    }

    inner class ViewHolder(binding: FragmentMovieListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieTitle: TextView = binding.title
        val releaseYear: TextView = binding.release
        val posterImage: ImageView = binding.moviePoster

    }
}