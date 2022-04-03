package com.example.chucknorrisapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.databinding.FragmentOneJokeBinding
import com.example.chucknorrisapp.model.ResultOne
import com.example.chucknorrisapp.viewmodel.ResultState
import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.DialogInterface

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneJokeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneJokeFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy {
        FragmentOneJokeBinding.inflate(layoutInflater)
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
        //Log.d("viewmodel", "before viewmodel")
        viewModel.jokes.observe(viewLifecycleOwner, ::handleState)
        Log.d("viewmodel", "after viewmodel")

        viewModel.getRandomJoke()
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
             .setNegativeButton("Dismiss", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                 })
             .create()
             .show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneJokeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneJokeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}