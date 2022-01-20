package com.noor.nytimes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.noor.nytimes.R
import com.noor.nytimes.data.NyTimesData
import com.noor.nytimes.mvvm.NyTimesViewModel
import com.noor.nytimes.ui.adapters.NyTimesArticlesAdapter
import com.noor.nytimes.utils.DataState
import kotlinx.android.synthetic.main.fragment_ny_times_articles.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.noor.nytimes.data.Record
import com.noor.nytimes.ui.activities.MainActivity
import com.noor.nytimes.utils.AppConstants.SHARED_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NyTimesArticles : Fragment(R.layout.fragment_ny_times_articles) {

    internal val viewModel: NyTimesViewModel by activityViewModels()
    private var adapter : NyTimesArticlesAdapter? = null
    var nyTimesData : NyTimesData? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        getArticles()
        subscribeObserver()

    }


    private fun bindViews() {

        //Set Title
        (activity as MainActivity).setToolbar(getString(R.string.new_york_times))

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun getArticles() {

        viewModel.getArticles()
    }

    private fun subscribeObserver() {

        viewModel.liveData.observe(viewLifecycleOwner,{ dataState->

            when(dataState) {

                DataState.Loading -> {
                   progressBar.visibility = View.VISIBLE

                }

                is DataState.Success -> {
                    progressBar.visibility = View.GONE
                    val response = dataState.data
                    response?.let { data->
                        processResponse(data)
                    }

                }
                is DataState.Error -> {
                    progressBar.visibility = View.GONE
                    showAlert(dataState.message)

                }
                else -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun showAlert(message: String) {
        MaterialAlertDialogBuilder(activity as MainActivity)
            .setMessage(message)
            .setNegativeButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun processResponse(data: NyTimesData) {
        nyTimesData = data
        nyTimesData?.let { response->
            adapter = NyTimesArticlesAdapter(response,requireContext())
            recyclerView.adapter = adapter
            registerItemClickListener()
        }

    }

    private fun registerItemClickListener() {
        adapter?.onItemClick = object :
            NyTimesArticlesAdapter.OnItemClickListener {
            override fun onItemClick(item: Record) {

                val bundle = bundleOf(SHARED_DATA to item)
                findNavController()
                    .navigate(
                        R.id.action_nyTimesArticles_to_articlesDetailsFragment,
                        bundle
                    )

            }
        }
    }


}