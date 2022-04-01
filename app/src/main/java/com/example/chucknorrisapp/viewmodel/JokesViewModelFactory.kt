package com.example.chucknorrisapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisapp.rest.JokesRepository

class JokesViewModelFactory(
    private val jokesRepository: JokesRepository
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokesViewModel(jokesRepository) as T
    }

}