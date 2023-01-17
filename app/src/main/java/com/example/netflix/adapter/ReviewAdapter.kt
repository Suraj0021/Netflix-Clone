package com.example.netflix.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.Activity.MovieDetailsActivity
import com.example.netflix.R
import com.example.netflix.dataClass.Movie
import com.example.netflix.dataClass.ReviewDataClass


class ReviewAdapter(private val context: Context, private val movies: List<ReviewDataClass>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieNameTextView: TextView = view.findViewById(R.id.loadMovieName)
        val gmail: TextView = view.findViewById(R.id.loadEmail)
        val comment: TextView = view.findViewById(R.id.loadMovieComment)
        val imageView: ImageView = view.findViewById(R.id.loadImage)
        val layout: LinearLayout = view.findViewById(R.id.moviesLayout)
        val loadRatingBar: RatingBar = view.findViewById(R.id.loadRating_bar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_layout, parent, false)
        Log.d("LoadImage", "movie.imageUrl.toString()")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieNameTextView.text = movie.movieName
        holder.gmail.text = "- "+movie.email
        holder.comment.text = movie.comment
        holder.loadRatingBar.rating = movie.ratings.toFloat()
        Log.d("LoadImage", "movie.imageUrl.toString()")
        Glide.with(holder.view.context).load(movie.imageUrl).into(holder.imageView)
//        holder.layout.setOnClickListener{
//
//            var intent = Intent(context, MovieDetailsActivity::class.java);
//            intent.putExtra("MovieName", movie.movieName)
//            intent.putExtra("MovieImage", movie.imageUrl)
//            intent.putExtra("MovieUrl", movie.movieUrl)
//            intent.putExtra("MovieLength", movie.length)
//            intent.putExtra("MovieC1", movie.categories.get(0))
//            intent.putExtra("MovieC2", movie.categories.get(1))
//            context.startActivity(intent)
//            (context as Activity).finish()
//
//        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
