package com.example.netflix.Activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.VISIBLE
import android.view.Window
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.R
import com.example.netflix.adapter.HistoryAdapter
import com.example.netflix.adapter.ReviewAdapter
import com.example.netflix.dataClass.Movies
import com.example.netflix.dataClass.ReviewDataClass
import com.example.netflix.json.getMovieJsonData
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.view.View
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
//import com.squareup.picasso.Picasso

class ReviewActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var card: CardView
    lateinit var movieNameDialog: TextView
    lateinit var loadImage: ImageView
    lateinit var progressBar: ProgressBar
    var movieNameList: ArrayList<String?> = ArrayList()
    var movieImageUrlList: ArrayList<String?> = ArrayList()
    var p = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        progressBar = findViewById(R.id.progress_bar2)

        card = findViewById(R.id.addReviewCard)
        card.setOnClickListener {
            val animator1 = ObjectAnimator.ofFloat(card, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(card, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            AddCard()
        }
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white))
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        var searchView: ImageView = toolbar.findViewById(R.id.searchView)

        searchView.setOnClickListener {
            startActivity(Intent(this@ReviewActivity, SearchActivity::class.java))
            finish()
        }

        var navigationView: NavigationView = findViewById(R.id.nvView)
        val headerView = navigationView.getHeaderView(0)
        var headerImage: ImageView = headerView.findViewById<ImageView>(R.id.navHeaderImage)



        navigationView.setNavigationItemSelectedListener {

                menuItem ->
            when (menuItem.itemId) {

                R.id.nav_dashboard -> {
                    startActivity(Intent(this@ReviewActivity, MainActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_movies -> {
                    startActivity(Intent(this@ReviewActivity, MoviesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_tv_shows -> {
                    startActivity(Intent(this@ReviewActivity, TVShowsActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_new_releases -> {
                    startActivity(Intent(this@ReviewActivity, NewReleasesActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_category -> {
                    startActivity(Intent(this@ReviewActivity, CategoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_reviews -> {
                    startActivity(Intent(this@ReviewActivity, ReviewActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_watchlist -> {
                    startActivity(Intent(this@ReviewActivity, WatchListActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this@ReviewActivity, HistoryActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {

                    drawer.closeDrawer(GravityCompat.START)

                    AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Do You Want To Logout?")
                        .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                            startActivity(Intent(this@ReviewActivity, LoginActivity::class.java))
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
        loaddata()
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

    fun loaddata() {
        progressBar.visibility = android.view.View.VISIBLE
        val gson = Gson()

        val movies = gson.fromJson(getMovieJsonData(), Movies::class.java).movies
        for (i in movies.filterNotNull().withIndex()) {
            movieNameList.add(movies[i.index].movieName)
            movieImageUrlList.add(movies[i.index].imageUrl)
        }

        val database = FirebaseDatabase.getInstance()
        val reviewsRef = database.getReference("Reviews")

        reviewsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get the data from the snapshot
                val reviews = dataSnapshot.children
                // Iterate over the reviews and create a MovieReview instance for each one
                val movieReviews = mutableListOf<ReviewDataClass>()
                reviews.forEach { review ->
                    val email = review.child("email").value as String
                    val image = review.child("imageUrl").value as String
                    val name = review.child("movieName").value as String
                    val comment = review.child("comment").value as String
                    val rating = review.child("ratings").value as String
                    movieReviews.add(ReviewDataClass(email, image, name, comment, rating))
                }

                val adapter =
                    ReviewAdapter(this@ReviewActivity, movieReviews.distinct().filterNotNull())
                val recyclerView = findViewById<RecyclerView>(R.id.rvReviews)
                recyclerView.layoutManager = LinearLayoutManager(this@ReviewActivity)
                recyclerView.adapter = adapter

                progressBar.visibility = android.view.View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // An error occurred
                println(error.toException())
            }
        })


    }

    fun AddCard() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.add_review_dialog)
        movieNameDialog = dialog.findViewById(R.id.movieName) as TextView
        val rating_bar = dialog.findViewById(R.id.rating_bar) as RatingBar
        val addComent = dialog.findViewById(R.id.addComment) as EditText
        loadImage = dialog.findViewById(R.id.loadImageDialog) as ImageView
        var closeReviewDialog = dialog.findViewById(R.id.closeReviewDialog) as ImageView
        val cancel = dialog.findViewById(R.id.cancelReview) as Button
        val submit = dialog.findViewById(R.id.submitReview) as Button
        rating_bar.rating = 0f

        movieNameDialog.setOnClickListener {
            getMovieListDialog()
        }

        cancel.setOnClickListener {
            dialog.dismiss()
        }

        closeReviewDialog.setOnClickListener {
            dialog.dismiss()
        }

        submit.setOnClickListener {
            var data = true
            if (addComent.text.toString().isEmpty()) {
                data = false
                addComent.setError("Add Comment Please")
            }
            if (rating_bar.rating.equals(0f)) {
                data = false
                Toast.makeText(this, "Please Add Star Ratings", Toast.LENGTH_SHORT).show()
            }
            if (movieNameDialog.text.equals("Select Movie Name *")) {
                data = false
                Toast.makeText(this, "Please select movie", Toast.LENGTH_SHORT).show()
            }
            if (data) {

                Toast.makeText(
                    this,
                    "" + FirebaseAuth.getInstance().currentUser?.email,
                    Toast.LENGTH_SHORT
                ).show()

                val m = ReviewDataClass(
                    FirebaseAuth.getInstance().currentUser?.email.toString(),
                    movieImageUrlList[p].toString(),
                    movieNameDialog.text.toString(),
                    addComent.text.toString(),
                    rating_bar.rating.toString()
                )

                FirebaseDatabase.getInstance().reference.child("Reviews").push().setValue(m)

                dialog.dismiss()
            }


        }

        dialog.show()


    }

    fun getMovieListDialog() {

        val dialog: Dialog = Dialog(this)


        // set custom dialog
        dialog.setContentView(R.layout.dialog_search_spinner);

        // set custom height and width
        dialog.window?.setLayout(950, 1400);

        // set transparent background

        // show dialog
        dialog.show();

        // Initialize and assign variable
        val editText: EditText = dialog.findViewById(R.id.edit_text);
        val listView: ListView = dialog.findViewById(R.id.list_view);

        // Initialize array adapter
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieNameList);

        // set adapter
        listView.adapter = adapter;

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable) {}
        })

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // when item selected from list
                // set selected item on textView
                movieNameDialog.text = adapter.getItem(position)
//
//                Picasso.get()
//                    .load(movieImageUrlList.get(position))
//                    .into(loadImage)

             Glide.with(this).load(movieImageUrlList.get(position)).into(loadImage)

                p = position
                loadImage.visibility = android.view.View.VISIBLE
                // Dismiss dialog
                dialog.dismiss()
            }
    }
}