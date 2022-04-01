package com.example.chucknorrisapp.rest

import com.example.chucknorrisapp.model.Joke
import com.example.chucknorrisapp.model.ResultOne
import com.example.chucknorrisapp.model.Results
import retrofit2.Response

interface JokesRepository {
    suspend fun getRandomJoke(): Response<ResultOne>
    suspend fun getRandomJokeByName(firstName:String, lastName:String): Response<ResultOne>
    suspend fun getRandomJokeByBatch(): Response<Results>
}

class JokesRepositoryImpl(
    private val jokeService: JokesApi
) : JokesRepository {

    override suspend fun getRandomJoke(): Response<ResultOne> =
        jokeService.getOneRandomJOke()

    override suspend fun getRandomJokeByName(firstName:String, lastName:String): Response<ResultOne> =
        jokeService.getOneRandomJOkeByName(firstName, lastName)

    override suspend fun getRandomJokeByBatch(): Response<Results> =
        jokeService.getRandomJOkeByBatch()

}