package com.example.chucknorrisapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisapp.rest.JokesRepository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule (
    private val applicationContext: Context
) {

    @Provides
    fun providesApplicationContext() = applicationContext



}