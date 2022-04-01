package com.example.chucknorrisapp.model


import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: List<Joke>
)