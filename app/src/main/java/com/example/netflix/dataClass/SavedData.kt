package com.example.netflix.dataClass



data class SavedData(val movies: List<Save>)

data class Save(
    val movieUrl: String,
    var imageUrl: String,
    var movieName: String,
    val categories: List<String>,
    val length: String
){
    constructor() : this("", "", "",  emptyList(),"")
}
