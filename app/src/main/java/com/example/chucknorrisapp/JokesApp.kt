package com.example.chucknorrisapp

import android.app.Application
import com.example.chucknorrisapp.di.ApplicationModule
import com.example.chucknorrisapp.di.DaggerJokesComponent
import com.example.chucknorrisapp.di.JokesComponent

class JokesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        jokesComponent = DaggerJokesComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var jokesComponent: JokesComponent
    }
}


