package com.ezt.runningapp.screen.subscreen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ezt.runningapp.R
import com.ezt.runningapp.base.BaseFragment
import com.ezt.runningapp.databinding.FragmentSetupBinding
import com.ezt.runningapp.utils.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import javax.inject.Inject

class SetupFragment : BaseFragment<FragmentSetupBinding>(FragmentSetupBinding::inflate) {
    @Inject
    lateinit var sharePref : SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }


        binding.tvContinue.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if(success) {
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean {
       binding.apply {
           val name  = etName.text.toString()
           val weight  = etWeight.text.toString()

           if(name.isNotEmpty() || weight.isEmpty()) {
               return false
           }
           sharePref.edit().putString(Constants.KEY_NAME, name)
               .putFloat(Constants.KEY_WEIGHT, weight.toFloat())
               .putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, false).apply()

           val toolbarText = "Let's go, $name"
           requireActivity().findViewById<MaterialTextView>(R.id.tvToolbarTitle).text = toolbarText
           return true
       }
    }
}