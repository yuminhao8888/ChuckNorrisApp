package com.example.chucknorrisapp.rest

import com.example.chucknorrisapp.model.Joke
import com.example.chucknorrisapp.model.ResultOne
import com.example.chucknorrisapp.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesApi {

    @GET(ONE_JOKE)
    suspend fun getOneRandomJOke(): Response<ResultOne>

    @GET(ONE_JOKE)
    suspend fun getOneRandomJOkeByName(
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String

    ): Response<ResultOne>

    @GET(ONE_BATCH_JOKE)
    suspend fun getRandomJOkeByBatch(): Response<Results>
//
//    @GET("{movie_id}")
//    suspend fun getAllDetailsMovies(
//        @Path("movie_id") movieID: Int,
//        @Query("api_key") apiKey: String = API_KEY,
//        @Query("language") language: String = LANGUAGE
//    ): Response<MoviesDetails>

    companion object{


        const val BASE_URL = "https://api.icndb.com/jokes/"

        const val BATCH_LENGTH = "20"
        //https://api.icndb.com/jokes/random
        private const val ONE_JOKE = "random/"

        private const val ONE_BATCH_JOKE = ONE_JOKE + BATCH_LENGTH




    }

}