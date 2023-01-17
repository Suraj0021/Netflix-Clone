package com.example.netflix.Activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.netflix.R
import com.example.netflix.adapter.MovieAdapter
import com.example.netflix.dataClass.Movies
import com.example.netflix.json.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var addData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var toolbar: Toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white))
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        var searchView: ImageView = toolbar.findViewById(R.id.searchView)

        searchView.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            finish()
        }

        var navigationView: NavigationView = findViewById(R.id.nvView)
        val headerView = navigationView.getHeaderView(0)
        var headerImage: ImageView = headerView.findViewById<ImageView>(R.id.navHeaderImage)

        navigationView.setNavigationItemSelectedListener {

                menuItem ->
            when (menuItem.itemId) {

                R.id.nav_dashboard -> {
                    startActivity(Intent(this@MainActivity, MainActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_movies -> {
                    startActivity(Intent(this@MainActivity, MoviesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_tv_shows -> {
                    startActivity(Intent(this@MainActivity, TVShowsActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_new_releases -> {
                    startActivity(Intent(this@MainActivity, NewReleasesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_category -> {
                    startActivity(Intent(this@MainActivity, CategoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_reviews -> {
                    startActivity(Intent(this@MainActivity, ReviewActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_watchlist -> {
                    startActivity(Intent(this@MainActivity, WatchListActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {

                    drawer.closeDrawer(GravityCompat.START)

                    AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Do You Want To Logout?")
                        .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            finish()
                            Firebase.auth.signOut()
                        }
                        .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->

                        }
                        .show()


                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false

            }


        }


        val openPopulerMovies: Button = findViewById(R.id.open_populer_movies)
        val open_populer_shows: Button = findViewById(R.id.open_populer_shows)
        val open_best_movies: Button = findViewById(R.id.open_best_movies)
        val open_top_movies: Button = findViewById(R.id.open_top_movies)

        openPopulerMovies.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MoviesAllActivity::class.java
                ).putExtra("openFrom", "Populer Movies")
            )
            finish()
        }

        open_populer_shows.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MoviesAllActivity::class.java
                ).putExtra("openFrom", "Top Rated")
            )
            finish()
        }

        open_best_movies.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MoviesAllActivity::class.java
                ).putExtra("openFrom", "Best Movies")
            )
            finish()
        }

        open_top_movies.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MoviesAllActivity::class.java
                ).putExtra("openFrom", "Top Movies")
            )
            finish()
        }



        loadData()
        if (intent.getStringExtra("splashScreen")?.isNotEmpty() == true) {
            if (savedInstanceState == null) {
                showDialogs()
            }

        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("onCreate", "data stored")

    }


    override fun onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)


        } else {
            // super.onBackPressed()
            AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do You Want To Exit?")
                .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                    super.onBackPressed()
                }
                .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->

                }
                .show()
        }


    }


    fun loadData() {

        val gson = Gson()
        val movies = gson.fromJson(getMovieJsonData(), Movies::class.java).movies

        val gson2 = Gson()
        val movies2 = gson2.fromJson(getPopulerMovieJsonData(), Movies::class.java).movies

        val movies3 = movies.filterNotNull()

        val rvPopulerMovies = findViewById<RecyclerView>(R.id.rvPopulerMovies)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvPopulerMovies.layoutManager = layoutManager
        rvPopulerMovies.adapter = MovieAdapter(this, movies2.filterNotNull())

        val gsonRec = Gson()
        val moviesRec = gsonRec.fromJson(getTopMovieJsonData(), Movies::class.java).movies
        val rvPopulerShows = findViewById<RecyclerView>(R.id.rvPopulerShows)
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvPopulerShows.layoutManager = layoutManager2
        rvPopulerShows.adapter = MovieAdapter(this, moviesRec.filterNotNull())

        val gsonBest = Gson()
        val moviesBest = gsonBest.fromJson(getBestMovieJsonData(), Movies::class.java).movies

        val rvBestMovies = findViewById<RecyclerView>(R.id.rvBestMovies)
        val layoutManager3 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvBestMovies.layoutManager = layoutManager3
        rvBestMovies.adapter = MovieAdapter(this, moviesBest.filterNotNull())

        val gsonTop = Gson()
        val moviesTop = gsonTop.fromJson(getRecommendedMovieJsonData(), Movies::class.java).movies
        val rvTopMovies = findViewById<RecyclerView>(R.id.rvTopMovies)
        val layoutManager4 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTopMovies.layoutManager = layoutManager4
        rvTopMovies.adapter = MovieAdapter(this, moviesTop.filterNotNull())

    }

    private fun showDialogs() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.latest_upload_dialog)
        val play = dialog.findViewById(R.id.playIntro) as TextView
        val view = dialog.findViewById(R.id.imageIntro) as ImageView
        val close = dialog.findViewById(R.id.closeIntro) as ImageView



//
//        Picasso.get()
//            .load("https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fpushpa.jpg?alt=media&token=430efc1f-21c4-4134-963f-f027a1ae0bb0")
//            .into(view)
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Images%2Fpushpa.jpg?alt=media&token=430efc1f-21c4-4134-963f-f027a1ae0bb0").into(view)
        close.setOnClickListener {
            dialog.dismiss()
        }

        play.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, PlayMovieActivity::class.java).putExtra(
                    "MovieUrlPlay",
                    "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/Movies%2FPushpa.mkv?alt=media&token=05001eae-f2e0-46f1-a304-4314a41ae913"
                )
            )
        }

        dialog.show()

    }
}