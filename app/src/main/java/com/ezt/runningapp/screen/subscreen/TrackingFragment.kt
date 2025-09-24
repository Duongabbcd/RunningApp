package com.ezt.runningapp.screen.subscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ezt.runningapp.base.BaseFragment
import com.ezt.runningapp.databinding.FragmentTrackingBinding
import com.ezt.runningapp.service.TrackingService
import com.ezt.runningapp.utils.Constants
import com.ezt.runningapp.viewmodel.RunningViewModel
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TrackingFragment : BaseFragment<FragmentTrackingBinding>(FragmentTrackingBinding::inflate) {
    private val viewModel: RunningViewModel by viewModels()

    private var map: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            mapView.onCreate(savedInstanceState)

            mapView.getMapAsync {
                map = it
            }

            btnToggleRun.setOnClickListener {
                sendCommandToService(Constants.ACTION_START_OR_RESUME_SERVICE)
            }

        }
    }

    private fun sendCommandToService(action: String)  =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }
}