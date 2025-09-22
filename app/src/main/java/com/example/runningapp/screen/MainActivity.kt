package com.example.runningapp.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.runningapp.R
import com.example.runningapp.base.BaseActivity
import com.example.runningapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupWithNavController

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            setSupportActionBar(toolbar)
            bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

            navHostFragment.findNavController()
                .addOnDestinationChangedListener { _, destination, _ ->
                    when(destination.id) {
                        R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                            bottomNavigationView.visibility = View.VISIBLE
                        else -> bottomNavigationView.visibility = View.GONE
                    }
                }
        }
    }
}