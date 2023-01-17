package com.example.netflix.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.adapter.MovieAdapter
import com.example.netflix.dataClass.Movies
import com.example.netflix.json.getMovieJsonData
import com.google.gson.Gson

class MoviesAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_all)
        var txtOpenMovies: TextView = findViewById(R.id.txtOpenMovies)
        txtOpenMovies.text = intent.getStringExtra("openFrom").toString()
        loadData()
    }

    fun loadData() {

        val gson = Gson()

        val movies = gson.fromJson(getMovieJsonData(), Movies::class.java).movies
        val movies2 = movies.filterNotNull()

        var recyclerView = findViewById<RecyclerView>(R.id.rvAllMovies)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(this, movies2)


    }

    override fun onBackPressed() {
        if (intent.getStringExtra("openFrom").toString()
                .contains("Movies") || intent.getStringExtra("openFrom").toString()
                .contains("Shows")
        ) {
            startActivity(Intent(this@MoviesAllActivity, MainActivity::class.java))
        } else {
            startActivity(Intent(this@MoviesAllActivity, CategoryActivity::class.java))
        }

        finish()

    }
}