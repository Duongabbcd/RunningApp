package com.ezt.runningapp.screen.subscreen

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ezt.runningapp.R
import com.ezt.runningapp.adapter.RunningAdapter
import com.ezt.runningapp.base.BaseFragment
import com.ezt.runningapp.databinding.FragmentRunningBinding
import com.ezt.runningapp.utils.TrackingUtility
import com.ezt.runningapp.viewmodel.RunningViewModel
import com.ezt.runningapp.viewmodel.SortType
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunningFragment : BaseFragment<FragmentRunningBinding>(FragmentRunningBinding::inflate), EasyPermissions.PermissionCallbacks {

    private val runningViewModel: RunningViewModel by viewModels()

    private lateinit var runningAdapter: RunningAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        binding.apply {
            runningAdapter = RunningAdapter()
            rvRuns.adapter = runningAdapter

            when(runningViewModel.sortType) {
                SortType.DATE -> spFilter.setSelection(0)
                SortType.RUNNING_TIME -> spFilter.setSelection(1)
                SortType.DISTANCE -> spFilter.setSelection(2)
                SortType.AVG_SPEED -> spFilter.setSelection(3)
                SortType.CALORIES_BURNED -> spFilter.setSelection(4)
            }

            spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                   when(position) {
                       0 -> runningViewModel.sortRuns(SortType.DATE)
                       1 -> runningViewModel.sortRuns(SortType.RUNNING_TIME)
                       2 -> runningViewModel.sortRuns(SortType.DISTANCE)
                       3 -> runningViewModel.sortRuns(SortType.AVG_SPEED)
                       4 -> runningViewModel.sortRuns(SortType.CALORIES_BURNED)
                   }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //do nothing
                }

            }

            runningViewModel.runs.observe(viewLifecycleOwner) {
                it.onEach {
                    Log.d(TAG, "onViewCreated: $it")
                }
                runningAdapter.submitList(it)
            }

            fab.setOnClickListener {
                findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
            }
        }
    }


    private fun requestPermissions() {
        if (TrackingUtility.hasLocationPermission(requireContext())) {
            return
        }

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
                )
        } else {
            EasyPermissions.requestPermissions(this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSION = 0
        private  val TAG = RunningFragment::class.java.simpleName
    }
}