package com.ezt.runningapp.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.ezt.runningapp.R
import com.ezt.runningapp.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupWithNavController
import com.ezt.runningapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            setSupportActionBar(toolbar)
            // ✅ Get the real NavController
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.navHostFragment) as NavHostFragment
            val navController = navHostFragment.navController

            bottomNavigationView.setupWithNavController(navController)

            // ✅ Handle destination changes
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.settingsFragment,
                    R.id.runFragment,
                    R.id.statisticsFragment -> bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }
}