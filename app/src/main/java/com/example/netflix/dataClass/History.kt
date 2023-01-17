package com.example.netflix.dataClass



data class History(val movies: List<historyData>)

data class historyData(
    val movieUrl: String,
    var imageUrl: String,
    var movieName: String,
    val categories: List<String>,
    val length: String,
    val time: String
){
    constructor() : this("", "", "",  emptyList(),"","")
}
