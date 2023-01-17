package com.example.netflix.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.adapter.MovieAdapter
import com.example.netflix.dataClass.Movies
import com.example.netflix.json.getMovieJsonData
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class TVShowsActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshows)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white))
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        var searchView : ImageView = toolbar.findViewById(R.id.searchView)

        searchView.setOnClickListener{
            startActivity(Intent(this@TVShowsActivity, SearchActivity::class.java))
            finish()
        }

        var navigationView: NavigationView = findViewById(R.id.nvView)
        val headerView = navigationView.getHeaderView(0)
        var headerImage: ImageView = headerView.findViewById<ImageView>(R.id.navHeaderImage)



        navigationView.setNavigationItemSelectedListener {

                menuItem ->
            when (menuItem.itemId) {

                R.id.nav_dashboard -> {
                    startActivity(Intent(this@TVShowsActivity, MainActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_movies -> {
                    startActivity(Intent(this@TVShowsActivity, MoviesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_tv_shows -> {
                    startActivity(Intent(this@TVShowsActivity, TVShowsActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_new_releases -> {
                    startActivity(Intent(this@TVShowsActivity, NewReleasesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_category -> {
                    startActivity(Intent(this@TVShowsActivity, CategoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_reviews -> {
                    startActivity(Intent(this@TVShowsActivity, ReviewActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_watchlist -> {
                    startActivity(Intent(this@TVShowsActivity, WatchListActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this@TVShowsActivity, HistoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {

                    drawer.closeDrawer(GravityCompat.START)

                    AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Do You Want To Logout?")
                        .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                            startActivity(Intent(this@TVShowsActivity, LoginActivity::class.java))
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

loadData()
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
        val movies2 = movies.filterNotNull()

        var recyclerView = findViewById<RecyclerView>(R.id.rvShowsList)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(this, movies2)


    }
}