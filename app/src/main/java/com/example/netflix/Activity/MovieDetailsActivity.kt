package com.example.netflix.Activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.R
import com.example.netflix.adapter.MovieAdapter
import com.example.netflix.dataClass.*
import com.example.netflix.json.getBestMovieJsonData
import com.example.netflix.json.getMovieJsonData
import com.example.netflix.json.getPopulerMovieJsonData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
//import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var movieName: TextView
    lateinit var watchNow: TextView
    lateinit var length: TextView
    lateinit var categories: TextView
    lateinit var watchTrailer: TextView
    lateinit var saveMovie: TextView
    lateinit var poster: ImageView
    var saved: Boolean = false
    val savedMoviesList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadData()
        movieName = findViewById(R.id.txtMovieTitle)
        watchNow = findViewById(R.id.txtWatchNow)
        watchTrailer = findViewById(R.id.watchTrailer)
        saveMovie = findViewById(R.id.saveMovie)
        poster = findViewById(R.id.imgMoviePoster)
        length = findViewById(R.id.moviesLength)
        categories = findViewById(R.id.moviesCategories)
        val url = intent.getStringExtra("MovieUrl")
        watchNow.setOnClickListener {

            val animator1 = ObjectAnimator.ofFloat(watchNow, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(watchNow, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()


            startActivity(
                Intent(this@MovieDetailsActivity, PlayMovieActivity::class.java).putExtra(
                    "MovieUrlPlay",
                    url
                )
            )

            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)

            val c1 = intent.getStringExtra("MovieC1").toString()
            val c2 = intent.getStringExtra("MovieC2").toString()

            val database = FirebaseDatabase.getInstance()
            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            val moviesRef =
                database.reference.child("UserHistoryData").child(userId).child("movies")
            val movie = historyData(
                url.toString(),
                intent.getStringExtra("MovieImage").toString(),
                movieName.text.toString(),
                listOf(c1, c2),
                intent.getStringExtra("MovieLength").toString(),formatted

            )
            val movieRef = moviesRef.push()
            movieRef.setValue(movie)

        }
        watchTrailer.setOnClickListener {
            val animator1 = ObjectAnimator.ofFloat(watchTrailer, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(watchTrailer, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            startActivity(
                Intent(this@MovieDetailsActivity, PlayMovieActivity::class.java).putExtra(
                    "MovieUrlPlay",
                    url
                )
            )
        }





        movieName.text = intent.getStringExtra("MovieName").toString()
//        Picasso.get()
//            .load(intent.getStringExtra("MovieImage").toString())
//            .into(poster)

        Glide.with(this).load(intent.getStringExtra("MovieImage").toString()).into(poster)


        val c1 = intent.getStringExtra("MovieC1").toString()
        val c2 = intent.getStringExtra("MovieC2").toString()
        categories.text = (c1 + "  " + c2)
        length.text = intent.getStringExtra("MovieLength").toString() + " mins"


        saveMovie.setOnClickListener {
            val animator1 = ObjectAnimator.ofFloat(saveMovie, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(saveMovie, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            if (saveMovie.text.toString() == " Save") {
                saveMovie.text = " Saved"
                Toast.makeText(this, "Movie Saved!!", Toast.LENGTH_SHORT).show()
            } else {
                saveMovie.text = " Save"
                Toast.makeText(this, "Movie Unsaved!!", Toast.LENGTH_SHORT).show()
            }

            val sharedPreferences = getSharedPreferences("usernames", Context.MODE_PRIVATE)
            val name = sharedPreferences.getString("username", "")
            val database = FirebaseDatabase.getInstance()
            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            val moviesRef = database.reference.child("UserSavedData").child(userId).child("movies")
            val movie = Save(
                url.toString(),
                intent.getStringExtra("MovieImage").toString(),
                movieName.text.toString(),
                listOf(c1, c2),
                intent.getStringExtra("MovieLength").toString(),
            )
            val movieRef = moviesRef.push()
            movieRef.setValue(movie)
        }
        loadData2()
    }

    override fun onBackPressed() {

        startActivity(Intent(this@MovieDetailsActivity, MainActivity::class.java))
        finish()

    }


    fun loadData() {

        val gson = Gson()

        val movies = gson.fromJson(getPopulerMovieJsonData(), Movies::class.java).movies
        val movies2 = movies.filterNotNull()

        val rvPopulerMovies = findViewById<RecyclerView>(R.id.recyclerview)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvPopulerMovies.layoutManager = layoutManager
        rvPopulerMovies.adapter = MovieAdapter(this, movies2)


        val gson2 = Gson()

        val movies3 = gson.fromJson(getBestMovieJsonData(), Movies::class.java).movies
        val movies4 = movies3.filterNotNull()

        val rvPopulerShows = findViewById<RecyclerView>(R.id.recyclerview2)
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvPopulerShows.layoutManager = layoutManager2
        rvPopulerShows.adapter = MovieAdapter(this, movies4)

    }


    private fun loadData2() {
        val database = FirebaseDatabase.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val moviesRef = database.reference.child("UserSavedData").child(userId).child("movies")

        moviesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val movie = snapshot.getValue(Movie::class.java)
                    if (movie != null) {
                        savedMoviesList.add(movie)
                        Log.w("TAG", "Failed to read value.  $movie")
                    }
                }

                for (i in savedMoviesList.indices) {

                    if (savedMoviesList[i].movieName.toString()
                            .contains(movieName.text.toString())
                    ) {
                        saveMovie.text = " Saved"
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this@MovieDetailsActivity, MainActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}