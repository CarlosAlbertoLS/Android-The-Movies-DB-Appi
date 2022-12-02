package com.sonder.movies.model

data class MovieDbResoult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)