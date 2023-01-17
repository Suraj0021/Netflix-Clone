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
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.Activity.MovieDetailsActivity
import com.example.netflix.R
import com.example.netflix.dataClass.Movie


class MovieAdapter(private val context: Context,private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieNameTextView: TextView = view.findViewById(R.id.txtMoviesName)
//        val categoriesTextView: TextView = view.findViewById(R.id.categoriesTextView)
//        val lengthTextView: TextView = view.findViewById(R.id.lengthTextView)
        val imageView: ImageView = view.findViewById(R.id.imgMovies)
        val layout: LinearLayout = view.findViewById(R.id.moviesLayout)
        val layoutCard: CardView = view.findViewById(R.id.cardMoviesAdapter)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout1, parent, false)
        Log.d("LoadImage","movie.imageUrl.toString()")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieNameTextView.text = movie.movieName
        //        holder.categoriesTextView.text = movie.categories.joinToString(", ")
//        holder.lengthTextView.text = "${movie.length} minutes"
        Log.d("LoadImage","movie.imageUrl.toString()")
        Glide.with(holder.view.context).load(movie.imageUrl).into(holder.imageView)
        holder.layout.setOnClickListener{


            val animator1 = ObjectAnimator.ofFloat(holder.layoutCard, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(holder.layoutCard, "scaleY", 1.2f, 1f)

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
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
