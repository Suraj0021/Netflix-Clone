package com.example.netflix.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.adapter.SavedAdapter
import com.example.netflix.dataClass.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class WatchListActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var saveEmpty: TextView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_list)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        progressBar = findViewById(R.id.progress_bar2)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white))
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        var navigationView: NavigationView = findViewById(R.id.nvView)
        val headerView = navigationView.getHeaderView(0)
        var headerImage: ImageView = headerView.findViewById<ImageView>(R.id.navHeaderImage)

        var searchView: ImageView = toolbar.findViewById(R.id.searchView)

        searchView.setOnClickListener {
            startActivity(Intent(this@WatchListActivity, SearchActivity::class.java))
            finish()
        }

        navigationView.setNavigationItemSelectedListener {

                menuItem ->
            when (menuItem.itemId) {

                R.id.nav_dashboard -> {
                    startActivity(Intent(this@WatchListActivity, MainActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_movies -> {
                    startActivity(Intent(this@WatchListActivity, MoviesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_tv_shows -> {
                    startActivity(Intent(this@WatchListActivity, TVShowsActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_new_releases -> {
                    startActivity(Intent(this@WatchListActivity, NewReleasesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_category -> {
                    startActivity(Intent(this@WatchListActivity, CategoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_reviews -> {
                    startActivity(Intent(this@WatchListActivity, ReviewActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_watchlist -> {
                    startActivity(Intent(this@WatchListActivity, WatchListActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this@WatchListActivity, HistoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {

                    drawer.closeDrawer(GravityCompat.START)

                    AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Do You Want To Logout?")
                        .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                            startActivity(Intent(this@WatchListActivity, LoginActivity::class.java))
                            intent.putExtra("logout","logout")
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
//        loadData()
        loadData2()

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



    private fun loadData2() {
        progressBar.visibility = android.view.View.VISIBLE
        val database = FirebaseDatabase.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val moviesRef = database.reference.child("UserSavedData").child(userId).child("movies")
        val movies = mutableListOf<Save>()
        val keyList = ArrayList<String>()
        moviesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val movie = snapshot.getValue(Save::class.java)
                    if (movie != null) {
                        movies.add(movie)
                        keyList.add(snapshot.key.toString())
                        Log.w("TAG", "Failed to read value.  $movie")
                    }
                }
                val adapter = SavedAdapter(this@WatchListActivity,movies.distinct(),keyList)
                val recyclerView = findViewById<RecyclerView>(R.id.rvSaveList)
                saveEmpty = findViewById<TextView>(R.id.saveEmpty)
                recyclerView.layoutManager = LinearLayoutManager(this@WatchListActivity)
                recyclerView.adapter = adapter
                if (movies.isEmpty()){
                    recyclerView.visibility = android.view.View.GONE
                   saveEmpty.visibility = android.view.View.VISIBLE
                }
                else{
                    saveEmpty.visibility = android.view.View.GONE
                    recyclerView.visibility = android.view.View.VISIBLE
                }
                progressBar.visibility = android.view.View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
                progressBar.visibility = android.view.View.GONE
            }
        })

    }

}

