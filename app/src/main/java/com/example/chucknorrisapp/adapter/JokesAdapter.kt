package com.example.chucknorrisapp.adapter

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.model.Joke
import com.squareup.picasso.Picasso

class JokesAdapter (
    private val jokes: MutableList<Joke> = mutableListOf(),
    //private val onFlowerClicked: (FlowersItem) -> Unit
) : RecyclerView.Adapter<JokesViewHolder>() {





    fun updateJokes(newJokes: List<Joke>) {
        //jokes.clear()
        jokes.addAll(newJokes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val jokeView = LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return JokesViewHolder(jokeView)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = jokes[position]
        holder.bind(joke)
    }

    override fun getItemCount(): Int = jokes.size
}

class JokesViewHolder(
    itemView: View
    //private val onFlowerClicked: (FlowersItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {



    val namedJoke: TextView = itemView.findViewById(R.id.textViewJoke)



    fun bind(joke: Joke) {
        namedJoke.text = joke.joke

    }
}