package com.example.chucknorrisapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.databinding.FragmentStartBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StartFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy {
        FragmentStartBinding.inflate(layoutInflater)
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

        binding.buttonRandomJoke.setOnClickListener{view ->
            //Log.d("cityname", weatherViewModel.getCityName())

            view.findNavController().navigate(R.id.action_StartFragment_to_OneJokeFragment)
        }

        binding.buttonTextInput.setOnClickListener{view ->
            //Log.d("cityname", weatherViewModel.getCityName())

            view.findNavController().navigate(R.id.action_StartFragment_to_TextInputFragment)
        }

        binding.buttonNeverEnding.setOnClickListener{view ->
            //Log.d("cityname", weatherViewModel.getCityName())

            view.findNavController().navigate(R.id.action_StartFragment_to_NeverEndingListFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}