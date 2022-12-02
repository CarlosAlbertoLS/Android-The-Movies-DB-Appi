package com.sonder.movies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.sonder.movies.R
import com.sonder.movies.databinding.ActivityDetailBinding
import com.sonder.movies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        title = movie?.title
        Glide
            .with(this)
            .load("https://image.tmdb.org/t/p/w780${movie?.backdrop_path}")
            .into(binding.backdrop)
        binding.sumary.text = movie?.overview
        bindDetailInfo(binding.detail, movie)
    }

    private fun bindDetailInfo(detail: TextView, movie: Movie?) {
        detail.text = buildSpannedString {
            appendInfo(R.string.original_language, movie?.original_language!!.uppercase())
            appendInfo(R.string.original_title, movie.original_title)
            appendInfo(R.string.release_date, movie.release_date)
            appendInfo(R.string.popularity, movie.popularity.toString())
            appendInfo(R.string.vote_average, movie.vote_average.toString())
        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String){
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }
}