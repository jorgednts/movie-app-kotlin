package com.example.movie_app_kotlin.presentation.movie_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app_kotlin.R
import com.example.movie_app_kotlin.domain.model.movie.MovieModel
import com.example.movie_app_kotlin.presentation.common.HorizontalListAdapter

class MovieCardAdapter(
    private var movieList: List<MovieModel>,
    private val onButtonClickListener: OnButtonClickListener
) :
    RecyclerView.Adapter<MovieCardAdapter.MovieViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newMovieList: List<MovieModel>?) {
        movieList = ArrayList(newMovieList!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.button.setOnClickListener {
            val movieId = movie.id
            onButtonClickListener.onButtonClick(movieId)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView
        private val releaseDateTextView: TextView
        private val ratingTextView: TextView
        private val posterImageView: ImageView
        val button: Button
        private val genreList: RecyclerView

        init {
            titleTextView = itemView.findViewById(R.id.movieTitle)
            releaseDateTextView = itemView.findViewById(R.id.releaseDate)
            ratingTextView = itemView.findViewById(R.id.rating)
            posterImageView = itemView.findViewById(R.id.poster)
            button = itemView.findViewById(R.id.button)
            genreList = itemView.findViewById(R.id.genreList)
        }

        fun bind(movie: MovieModel) {
            titleTextView.text = movie.title
            releaseDateTextView.text = movie.releaseDate
            ratingTextView.text = String.format(movie.voteAverage.toString())
            Glide.with(itemView.context)
                .load(movie.posterUrl)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(posterImageView)
            genreList.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val adapter = HorizontalListAdapter(movie.genres)
            genreList.adapter = adapter
        }
    }
}