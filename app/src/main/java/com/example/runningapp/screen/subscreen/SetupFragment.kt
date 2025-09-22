package com.example.runningapp.screen.subscreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.runningapp.R
import com.example.runningapp.base.BaseFragment
import com.example.runningapp.databinding.FragmentSetupBinding

class SetupFragment : BaseFragment<FragmentSetupBinding>(FragmentSetupBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvContinue.setOnClickListener {
            findNavController().navigate(R.id.action_setupFragment_to_runFragment)
        }
    }
}