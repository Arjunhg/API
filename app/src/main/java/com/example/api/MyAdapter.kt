package com.example.api

import android.app.Activity
import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity, val productArrayList : List<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){ //Adapter extends this way


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { //This will tell how each item would look
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //Used to populate data.
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
        // image view , how to show image in image view if the image is in form of url, 3rd Party Library
        // Picasso
        Picasso.get().load(currentItem.thumbnail).into(holder.image); //used thumbnail to get URL
        holder.rating.rating = currentItem.rating.toFloat()

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){ //Holds view
         var title : TextView
         var image : ShapeableImageView
         var rating : RatingBar

        init { //Initializing
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImage)
            rating = itemView.findViewById(R.id.barRating)
        }
    }

}