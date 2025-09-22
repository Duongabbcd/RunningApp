package com.example.runningapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.runningapp.base.BaseActivity
import com.example.runningapp.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

        }
    }
}