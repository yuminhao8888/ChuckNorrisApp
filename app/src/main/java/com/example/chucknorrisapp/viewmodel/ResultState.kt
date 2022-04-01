package com.example.chucknorrisapp.viewmodel

import com.example.chucknorrisapp.model.Results

sealed class ResultState {
    object LOADING : ResultState()
    class SUCCESS<T>(val response: T): ResultState()
    class ERROR(val error: Throwable) : ResultState()
}