package com.example.netflix.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.adapter.MovieAdapter
import com.example.netflix.dataClass.Movie
import com.example.netflix.dataClass.Movies

import com.example.netflix.json.*
import com.google.gson.Gson


class SearchActivity : AppCompatActivity() {
    lateinit var search: EditText
    lateinit var backToHome: ImageView
    lateinit var found: TextView
    var seachedText: String = ""
    lateinit var gson: Gson
    lateinit var movies: List<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gson = Gson()

        movies = gson.fromJson(getMovieJsonData(), Movies::class.java).movies

        search = findViewById(R.id.serachMovie)
        found = findViewById(R.id.txtMoviesFound)
        backToHome = findViewById(R.id.backToHome)
        backToHome.setOnClickListener {
            startActivity(Intent(this@SearchActivity, MainActivity::class.java))
            finish()
        }

        search.requestFocus()
        search.selectAll()

        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count == 0) {
                    loadAllData()
                } else {
                    seachedText = s.toString()
                    loadData()
                }
                // This method is called to notify you that, within s, the count characters beginning at start have just replaced old text that had length before.
            }

            override fun afterTextChanged(s: Editable) {
                // This method is called to notify you that, somewhere within s, the text has been changed.
            }
        })

        loadAllData()
    }

    override fun onBackPressed() {

        startActivity(Intent(this@SearchActivity, MainActivity::class.java))
        finish()
    }

    @SuppressLint("SetTextI18n")
    fun loadData() {


        val subList = movies.filterNotNull()
            .filter { it.movieName.uppercase().contains(seachedText.uppercase()) }

        Log.d(
            "FilterList",
            "" + movies.filterNotNull()
                .filter { it.movieName.uppercase().contains(seachedText.uppercase()) })


        var recyclerView = findViewById<RecyclerView>(R.id.rvMoviesList)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = subList.let { MovieAdapter(this, subList) }
        println("Original list: $movies")
        println("Filtered list: $subList")
        println("Predicate: ${seachedText.uppercase()}")
        found.text = "${subList.size} Movies Found"
    }

    @SuppressLint("SetTextI18n")
    fun loadAllData() {
        val movies2: List<Movie> = ArrayList()
        var recyclerView = findViewById<RecyclerView>(R.id.rvMoviesList)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(this, movies2)
        found.text = "${movies2.size} Movies Found"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this@SearchActivity, MainActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
