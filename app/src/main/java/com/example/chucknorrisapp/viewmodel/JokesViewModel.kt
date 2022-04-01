package com.example.chucknorrisapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisapp.model.ResultOne
import com.example.chucknorrisapp.model.Results
import com.example.chucknorrisapp.rest.JokesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class JokesViewModel (
    private val jokesRepository: JokesRepository,

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

     public var name = ""

     public var isScreenRotated = false

    init {
        Log.d("viewmodel","view model is created")
    }

    //var platform: PlatformType = PlatformType.ANDROID

    private val _jokes: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val jokes: LiveData<ResultState> get() = _jokes

    fun getRandomJoke() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokesRepository.getRandomJoke()
                if(response.isSuccessful) {
                    response.body()?.let {
//                        databaseRepo.insertGiveaways(it)
//                        val localData = databaseRepo.getAllGiveaways()
                        _jokes.postValue(ResultState.SUCCESS<ResultOne>(it))
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                // we catch all errors
                _jokes.postValue(ResultState.ERROR(e))
                //loadFromDB()
            }
        }
    }

    fun getRandomJokeByName(firstName:String, lastName:String) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokesRepository.getRandomJokeByName(firstName,lastName)
                if(response.isSuccessful) {
                    response.body()?.let {
//                        databaseRepo.insertGiveaways(it)
//                        val localData = databaseRepo.getAllGiveaways()
                        _jokes.postValue(ResultState.SUCCESS<ResultOne>(it))
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                // we catch all errors
                _jokes.postValue(ResultState.ERROR(e))
                //loadFromDB()
            }
        }
    }

    fun getRandomJokeByBatch() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = jokesRepository.getRandomJokeByBatch()
                if(response.isSuccessful) {
                    response.body()?.let {
//                        databaseRepo.insertGiveaways(it)
//                        val localData = databaseRepo.getAllGiveaways()
                        _jokes.postValue(ResultState.SUCCESS<Results>(it))
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                // we catch all errors
                _jokes.postValue(ResultState.ERROR(e))
                //loadFromDB()
            }
        }
    }
//
//    private suspend fun loadFromDB() {
//        try {
//            val localData = databaseRepo.getAllGiveaways()
//            _sortedGiveaways.postValue(GiveawayState.SUCCESS(localData, isLocalData = true))
//        } catch (e: Exception) {
//            _sortedGiveaways.postValue(GiveawayState.ERROR(e))
//        }
//    }
//
//    fun getGiveawaysByPlatform() {
//        viewModelScope.launch(ioDispatcher) {
//            try {
//                val response = networkRepo.getGiveawaysByPlatform(platform)
//                if(response.isSuccessful) {
//                    response.body()?.let {
//                        _sortedGiveaways.postValue(GiveawayState.SUCCESS(it))
//                    } ?: throw Exception("Response in null")
//                } else {
//                    throw Exception("No successful response")
//                }
//            } catch (e: Exception) {
//                _sortedGiveaways.postValue(GiveawayState.ERROR(e))
//                loadFromDBByPlatform()
//            }
//        }
//
//
//    }
//
//    private suspend fun loadFromDBByPlatform() {
//        try {
//            val localData = databaseRepo.getGiveawaysByPlatform(platform.realValue)
//            _sortedGiveaways.postValue(GiveawayState.SUCCESS(localData, isLocalData = true))
//        } catch (e: Exception) {
//            _sortedGiveaways.postValue(GiveawayState.ERROR(e))
//        }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d("GiveawaysViewModel", "VIEWMODEL destroyed")
//    }
}