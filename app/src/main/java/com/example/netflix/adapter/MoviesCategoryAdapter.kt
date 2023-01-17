package com.example.netflix.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.Activity.MoviesAllActivity
import com.example.netflix.R


class MoviesCategoryAdapter(private val context: Context, private val list: ArrayList<String?>) : RecyclerView.Adapter<MoviesCategoryAdapter.MyView>() {

    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {

        var textView: TextView
        init { textView = view.findViewById<View>(R.id.txtCategory) as TextView
        }

        var layout: LinearLayout
        init { layout = view.findViewById<View>(R.id.layoutCategory) as LinearLayout
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout2, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {



        holder.textView.text = list.get(position)

        holder.layout.setOnClickListener{
            val animator1 = ObjectAnimator.ofFloat(holder.layout, "scaleX", 1.2f, 1f)
            val animator2 = ObjectAnimator.ofFloat(holder.layout, "scaleY", 1.2f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            var intent = Intent(context, MoviesAllActivity::class.java);
            intent.putExtra("openFrom",list.get(position) )
            context.startActivity(intent)
            (context as Activity).finish()

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}