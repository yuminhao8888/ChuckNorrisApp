package com.example.chucknorrisapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisapp.JokesApp
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.rest.JokesRepository
import com.example.chucknorrisapp.viewmodel.JokesViewModel
import com.example.chucknorrisapp.viewmodel.JokesViewModelFactory
import javax.inject.Inject


open class BaseFragment : Fragment() {
    @Inject
    lateinit var jokesRepository: JokesRepository

    val viewModel by lazy {
        ViewModelProvider(requireActivity(), JokesViewModelFactory(jokesRepository))[JokesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        JokesApp.jokesComponent.inject(this)
    }
}