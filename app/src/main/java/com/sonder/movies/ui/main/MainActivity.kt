package com.sonder.movies.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sonder.movies.R
import com.sonder.movies.databinding.ActivityMainBinding
import com.sonder.movies.model.Movie
import com.sonder.movies.model.MoviesDbClient
import com.sonder.movies.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesAdapter = MoviesAdapter(emptyList()) { navigateTo(it) }

        binding.rvMovies.adapter = moviesAdapter

        lifecycleScope.launch {
            val apikey = getString(R.string.apiKey)
            val popularMovie = MoviesDbClient.service.listPopularMovies(apikey)
            moviesAdapter.movies = popularMovie.results
            moviesAdapter.notifyDataSetChanged()

        }
    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}