package com.example.netflix.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.Activity.HistoryActivity
import com.example.netflix.Activity.MovieDetailsActivity
import com.example.netflix.Activity.WatchListActivity
import com.example.netflix.R
import com.example.netflix.dataClass.Movie
import com.example.netflix.dataClass.Save
import com.example.netflix.dataClass.historyData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SavedAdapter(
    private val context: Context,
    private val movies: List<Save>,
    private val keyList: List<String>
) :
    RecyclerView.Adapter<SavedAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieNameTextView: TextView = view.findViewById(R.id.txtMoviesName)

        //        val categoriesTextView: TextView = view.findViewById(R.id.categoriesTextView)
//        val lengthTextView: TextView = view.findViewById(R.id.lengthTextView)
        val imageView: ImageView = view.findViewById(R.id.imgMovies)
        val layout: LinearLayout = view.findViewById(R.id.moviesLayout)
        val category: TextView = view.findViewById(R.id.txtMoviesCategorySave)
        val save: TextView = view.findViewById(R.id.playMovieSave)
        val clear: TextView = view.findViewById(R.id.clearMovieSave)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_layout3, parent, false)
        Log.d("LoadImage", "movie.imageUrl.toString()")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieNameTextView.text = movie.movieName
        holder.category.text = movie.categories.get(0) + ", " + movie.categories.get(1)

        //        holder.categoriesTextView.text = movie.categories.joinToString(", ")
//        holder.lengthTextView.text = "${movie.length} minutes"
        Log.d("LoadImage", "movie.imageUrl.toString()")
        Glide.with(holder.view.context).load(movie.imageUrl).into(holder.imageView)
        holder.layout.setOnClickListener {
            val animator1 = ObjectAnimator.ofFloat(holder.layout, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(holder.layout, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            var intent = Intent(context, MovieDetailsActivity::class.java);
            intent.putExtra("MovieName", movie.movieName)
            intent.putExtra("MovieImage", movie.imageUrl)
            intent.putExtra("MovieUrl", movie.movieUrl)
            intent.putExtra("MovieLength", movie.length)
            intent.putExtra("MovieC1", movie.categories.get(0))
            intent.putExtra("MovieC2", movie.categories.get(1))
            context.startActivity(intent)
            (context as Activity).finish()

        }
        holder.save.setOnClickListener {
            val animator1 = ObjectAnimator.ofFloat(holder.save, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(holder.save, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            var intent = Intent(context, MovieDetailsActivity::class.java);
            intent.putExtra("MovieName", movie.movieName)
            intent.putExtra("MovieImage", movie.imageUrl)
            intent.putExtra("MovieUrl", movie.movieUrl)
            intent.putExtra("MovieLength", movie.length)
            intent.putExtra("MovieC1", movie.categories.get(0))
            intent.putExtra("MovieC2", movie.categories.get(1))
            context.startActivity(intent)
            (context as Activity).finish()

        }

        holder.clear.setOnClickListener {
            val animator1 = ObjectAnimator.ofFloat(holder.clear, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(holder.clear, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            val savedMoviesList = mutableListOf<Save>()

            val database = FirebaseDatabase.getInstance()
            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            val moviesRef =
                database.reference.child("UserSavedData").child(userId).child("movies")

            moviesRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (snapshot in dataSnapshot.children) {
                        val movie = snapshot.getValue(Save::class.java)
                        if (movie != null) {
                            if (snapshot.key.toString() == keyList.get(position)) {
                                moviesRef.child(snapshot.key.toString()).removeValue()
                                Log.d(
                                    "KeyValues",
                                    "" + snapshot.key.toString() + " , " + keyList[position]
                                )
                                context.startActivity(Intent(context,WatchListActivity::class.java))
                                (context as WatchListActivity).finish()
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })


        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
