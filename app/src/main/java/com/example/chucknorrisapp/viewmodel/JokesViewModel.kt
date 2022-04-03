package com.example.chucknorrisapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisapp.model.Results
import com.example.chucknorrisapp.rest.JokesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokesViewModel (
    private val jokesRepository: JokesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var name = ""

    init {
        Log.d("viewmodel","view model is created")
    }

    private val _jokes: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val jokes: LiveData<ResultState> get() = _jokes

    fun getRandomJoke() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokesRepository.getRandomJoke()
                if(response.isSuccessful) {
                    response.body()?.let {
                        _jokes.postValue(ResultState.SUCCESS(it))
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                // we catch all errors
                _jokes.postValue(ResultState.ERROR(e))
            }
        }
    }

    fun getRandomJokeByName(firstName:String, lastName:String) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokesRepository.getRandomJokeByName(firstName,lastName)
                if(response.isSuccessful) {
                    response.body()?.let {
                        _jokes.postValue(ResultState.SUCCESS(it))
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                // we catch all errors
                _jokes.postValue(ResultState.ERROR(e))
            }
        }
    }

    fun getRandomJokeByBatch() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokesRepository.getRandomJokeByBatch()
                if(response.isSuccessful) {
                    response.body()?.let {
                        _jokes.postValue(ResultState.SUCCESS<Results>(it))
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                // we catch all errors
                _jokes.postValue(ResultState.ERROR(e))
            }
        }
    }
}