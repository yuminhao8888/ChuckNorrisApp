package com.example.chucknorrisapp.model


import com.google.gson.annotations.SerializedName

data class ResultOne(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: Joke
)