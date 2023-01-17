package com.example.netflix.Activity


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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


class MoviesActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white))
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        var searchView : ImageView = toolbar.findViewById(R.id.searchView)

        searchView.setOnClickListener{
            startActivity(Intent(this@MoviesActivity, SearchActivity::class.java))
            finish()
        }

        var navigationView: NavigationView = findViewById(R.id.nvView)
        val headerView = navigationView.getHeaderView(0)
        var headerImage: ImageView = headerView.findViewById<ImageView>(R.id.navHeaderImage)



        navigationView.setNavigationItemSelectedListener {

                menuItem ->
            when (menuItem.itemId) {

                R.id.nav_dashboard -> {
                    startActivity(Intent(this@MoviesActivity, MainActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_movies -> {
                    startActivity(Intent(this@MoviesActivity, MoviesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_tv_shows -> {
                    startActivity(Intent(this@MoviesActivity, TVShowsActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_new_releases -> {
                    startActivity(Intent(this@MoviesActivity, NewReleasesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_category -> {
                    startActivity(Intent(this@MoviesActivity, CategoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_reviews -> {
                    startActivity(Intent(this@MoviesActivity, ReviewActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_watchlist -> {
                    startActivity(Intent(this@MoviesActivity, WatchListActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this@MoviesActivity, HistoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {

                    drawer.closeDrawer(GravityCompat.START)

                    AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Do You Want To Logout?")
                        .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                            startActivity(Intent(this@MoviesActivity, LoginActivity::class.java))
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

    fun loadData() {

        val gson = Gson()

        val movies = gson.fromJson(getMovieJsonData(), Movies::class.java).movies
        val movies2 = movies.filterNotNull()

      var  recyclerView = findViewById<RecyclerView>(R.id.rvMoviesList)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(this, movies2)



    }


//    fun topMovies() {
//        var recyclerView: RecyclerView
//        var source: ArrayList<MoviesModelClass?>
//        var adapter: MoviesListAdapter
//
//        source = ArrayList()
//
//        source.add(
//            MoviesModelClass(
//                "Dhamaka",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerShows%2FDhamaka.jpg?alt=media&token=315038bd-f9b9-42d0-b959-5fcfd396802a"
//            )
//        )
//        source.add(
//            MoviesModelClass(
//                "Goodbye",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerShows%2FGoodBye.jpg?alt=media&token=2018ff25-9a2d-49f2-811f-870ddd78667b"
//            )
//        )
//
//        source.add(
//            MoviesModelClass(
//                "Jersey",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerShows%2FJersey.jpg?alt=media&token=e1b9f4b4-29df-4d1f-828d-3f52c3188a49"
//            )
//        )
//        source.add(
//            MoviesModelClass(
//                "RRR",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerShows%2FRRR.jpg?alt=media&token=3bcf9801-8a4f-4d3b-9b55-3888b6588f93"
//            )
//        )
//
//        source.add(
//            MoviesModelClass(
//                "Bhool Bhoolaiyya 2",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerShows%2FBhoolBhulaiyya.jpg?alt=media&token=d2524921-5e3a-4cf4-b3e1-12fe59b73f93"
//            )
//        )
//
//        source.add(
//            MoviesModelClass(
//                "All Of Us Are Dead",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerMovies%2FAllOfUsAreDead.jpg?alt=media&token=b25ca01a-a658-4b06-a153-616233c08f6d"
//            )
//        )
//        source.add(
//            MoviesModelClass(
//                "1899",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerMovies%2F1899.jpg?alt=media&token=7533579f-89c4-4650-a1ec-f37dd7238c6b"
//            )
//        )
//        source.add(
//            MoviesModelClass(
//                "Hounting Of Hill House",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerMovies%2FHountingOfHIllHouse.jpg?alt=media&token=87da8446-24d0-4af5-9732-c8ff1add1a2b"
//            )
//        )
//        source.add(
//            MoviesModelClass(
//                "Dark",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerMovies%2Fdark.jpg?alt=media&token=34cb8b3f-e4d0-475c-bd33-3cbac4f9f8e7"
//            )
//        )
//        source.add(
//            MoviesModelClass(
//                "Squid Game",
//                "https://firebasestorage.googleapis.com/v0/b/netflix-6cfee.appspot.com/o/PopulerMovies%2FSquidGame.jpg?alt=media&token=6197db4a-d31a-4bf3-9295-6806844dd695"
//            )
//        )
//
//        recyclerView = findViewById(R.id.rvMoviesList)
//
//        val layoutManager = GridLayoutManager(this, 3)
//
//        adapter = MoviesListAdapter(this, source)
//
//        recyclerView.layoutManager = layoutManager
//
//        recyclerView.adapter = adapter
//
//    }


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
}