package com.example.moviedom.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.moviedom.data.MovieDetails

import com.example.moviedom.databinding.FragmentMovieListBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MovieRecyclerViewAdapter(
    private var values: List<MovieDetails>
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieListBinding.inflate(
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
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieTitle: TextView = binding.title
        val releaseYear: TextView = binding.release
        val posterImage:ImageView = binding.moviePoster

    }

}