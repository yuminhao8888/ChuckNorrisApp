package com.example.chucknorrisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chucknorrisapp.databinding.ActivityMainBinding
import com.example.chucknorrisapp.rest.JokesRepository
import com.example.chucknorrisapp.viewmodel.JokesViewModel
import com.example.chucknorrisapp.viewmodel.JokesViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("destroy", "activity is created")
        JokesApp.jokesComponent.inject(this)

        val navController = findNavController(R.id.frag_nav_container)
        setupActionBarWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("destroy", "activity is destroyed")
    }
}