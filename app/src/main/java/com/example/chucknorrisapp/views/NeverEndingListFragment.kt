package com.example.chucknorrisapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisapp.MainActivity
import com.example.chucknorrisapp.R
import com.example.chucknorrisapp.adapter.JokesAdapter
import com.example.chucknorrisapp.databinding.FragmentNeverEndingListBinding
import com.example.chucknorrisapp.model.ResultOne
import com.example.chucknorrisapp.model.Results
import com.example.chucknorrisapp.viewmodel.ResultState

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NeverEndingListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NeverEndingListFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy {
        FragmentNeverEndingListBinding.inflate(layoutInflater)
    }

    private val jokeAdapter by lazy {
        JokesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d("destroy", "fragment is created")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.progressBar.visibility = View.GONE

        binding.jokeRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokeAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!binding.jokeRecycler.canScrollVertically(1)) {
                        binding.progressBar.visibility = View.VISIBLE
                        viewModel.getRandomJokeByBatch()
                    }
                }
            })
        }

        Log.d("observe", "before observe")
        viewModel.jokes.observe(viewLifecycleOwner) { resultState ->
            Log.d("observe", "before when")
            when(resultState) {
                is ResultState.LOADING -> {
                    Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
                }
                is ResultState.SUCCESS<*> -> {
                    Log.d("observe", "success")
                    binding.progressBar.visibility = View.GONE
                    val jokes = (resultState as ResultState.SUCCESS<Results>).response.value
                    jokeAdapter.updateJokes(jokes)
                    Log.d("joke12", jokes[0].joke)
                }
                is ResultState.ERROR -> {
                    Log.e("FORECAST", resultState.error.localizedMessage, resultState.error)
                    Toast.makeText(requireContext(), resultState.error.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }


        viewModel.getRandomJokeByBatch()

        Log.d("screen", "after not screen rotated")

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("destroy", "fragment is destroyed")
    }

    private fun handleState(resultState: ResultState) {
        when(resultState) {
            is ResultState.LOADING -> {
                Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
            }
            is ResultState.SUCCESS<*> -> {
                val jokes = (resultState as ResultState.SUCCESS<Results>).response.value
                jokeAdapter.updateJokes(jokes)
                Log.d("joke12", jokes[0].joke)
            }
            is ResultState.ERROR -> {
                Log.e("FORECAST", resultState.error.localizedMessage, resultState.error)
                Toast.makeText(requireContext(), resultState.error.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NeverEndingListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NeverEndingListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}