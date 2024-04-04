package com.example.moviedom.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.moviedom.R
import com.example.moviedom.data.MovieDetails

import com.example.moviedom.databinding.FragmentMovieListItemsBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MovieRecyclerViewAdapter(
    private var values: List<MovieDetails>
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    fun setMovieItemList(scanList: List<MovieDetails>) {
        values = scanList as MutableList<MovieDetails>
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.movieTitle.text = item.title
        holder.releaseYear.text = item.year

        holder.posterImage.load(item.poster) {
            crossfade(true)
            placeholder(R.drawable.no_image)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMovieListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieTitle: TextView = binding.title
        val releaseYear: TextView = binding.release
        val posterImage:ImageView = binding.moviePoster

    }

}