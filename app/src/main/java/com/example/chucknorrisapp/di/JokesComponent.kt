package com.example.chucknorrisapp.di

import com.example.chucknorrisapp.MainActivity
import com.example.chucknorrisapp.views.BaseFragment
import com.example.chucknorrisapp.views.StartFragment
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        ApplicationModule::class

    ]
)
interface JokesComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)
    //fun inject(starShipsFragment: StarShipsFragment)
    //fun inject(planetsFragment: PlanetsFragment)
}


