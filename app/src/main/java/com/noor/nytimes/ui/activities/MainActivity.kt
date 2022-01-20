package com.noor.nytimes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.noor.nytimes.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var toolbarBackBtn: ImageView
    private lateinit var toolbarTitleTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        bindViews()
        onBackButtonClicked()
        setStartDestination()
    }

    private fun bindViews() {

        toolbar = findViewById(R.id.toolbar)
        toolbarTitleTv = toolbar.findViewById(R.id.tvTitle)
        toolbarBackBtn = toolbar.findViewById(R.id.ivBack)

    }


    /**
     * Set the toolbar
     *
     * @param toolbarTitle the title of toolbar
     */
    fun setToolbar(toolbarTitle: String) {
        toolbarTitleTv.text = toolbarTitle
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    /**
     * Register Back Button Click Listener
     */
    private fun onBackButtonClicked() {
        toolbarBackBtn.setOnClickListener { onBackPressed() }
    }

    private fun setStartDestination()
    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_graph)

        val navController = navHostFragment.navController
        navController.graph = graph

    }


}