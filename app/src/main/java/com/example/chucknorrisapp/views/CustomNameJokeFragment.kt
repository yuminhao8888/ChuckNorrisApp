package com.example.chucknorrisapp.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.databinding.FragmentCustomNameJokeBinding
import com.example.chucknorrisapp.model.ResultOne
import com.example.chucknorrisapp.viewmodel.ResultState

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CustomNameJokeFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy {
        FragmentCustomNameJokeBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // get name from view model and specify first name and last name
        val name = viewModel.name
        val firstName = name.split("\\s".toRegex())[0]
        val lastName = name.split("\\s".toRegex())[1]

        viewModel.jokes.observe(viewLifecycleOwner, ::handleState)
        Log.d("viewmodel", "after viewmodel")

        viewModel.getRandomJokeByName(firstName,lastName)
        return binding.root
    }

    private fun handleState(resultState: ResultState) {
        when(resultState) {
            is ResultState.LOADING -> {
                Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
            }
            is ResultState.SUCCESS<*> -> {
                val joke = (resultState as ResultState.SUCCESS<ResultOne>).response.value.joke
                Log.d("joke1", joke)
                displayInDialog(joke)
                //ResultState.SUCCESS<ResultOne>
                //weatherAdapter.setForecast(resultState.results.value)
            }
            is ResultState.ERROR -> {
                Log.e("FORECAST", resultState.error.localizedMessage, resultState.error)
                Toast.makeText(requireContext(), resultState.error.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun displayInDialog(joke:String){
        //Toast.makeText(requireContext(), joke, Toast.LENGTH_LONG)
        AlertDialog.Builder(context)
            .setMessage(joke)
            .setCancelable(false)
            .setNegativeButton("Dismiss") { dialog, id ->
                dialog.cancel()
            }
            .create()
            .show()
    }
}