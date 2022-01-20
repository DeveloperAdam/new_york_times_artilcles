package com.noor.nytimes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.noor.nytimes.R
import com.noor.nytimes.data.Record
import com.noor.nytimes.utils.AppConstants.SHARED_DATA
import kotlinx.android.synthetic.main.fragment_article_details.*


class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {

    private var resultData : Record? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            resultData = bundle.getParcelable(SHARED_DATA)
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        registerBackCall()
    }


    private fun registerBackCall() {

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Navigation.findNavController(requireView()).popBackStack(R.id.nyTimesArticles,false)
                }
            })

    }

    private fun bindViews() {


        resultData?.let { data ->
            tvTitle.text = data.title
            tvDescription.text = data._abstract
            tvCreatedByDes.text = data.byline
            tvSourceDes.text = data.source
            Glide.with(requireContext()).load(data.media[0].mediaMetadata[2].url).fitCenter().placeholder(R.drawable.newspaper).into(ivNewsImage)
        }

    }


}