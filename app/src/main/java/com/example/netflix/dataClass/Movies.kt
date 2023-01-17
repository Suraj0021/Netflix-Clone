package com.example.netflix.dataClass



data class Movies(val movies: List<Movie>)

data class Movie(
    val movieUrl: String,
    var imageUrl: String,
    var movieName: String,
    val categories: List<String>,
    val length: String
){
    constructor() : this("", "", "",  emptyList(),"")
}
