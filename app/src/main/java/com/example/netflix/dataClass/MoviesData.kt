package com.example.netflix.dataClass
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MovieData(val movieName: String, val movieUrl: String,val imageUrl: String,val length: String,val categories: List<String>) {
    constructor() : this("", "", "", "", emptyList())
}