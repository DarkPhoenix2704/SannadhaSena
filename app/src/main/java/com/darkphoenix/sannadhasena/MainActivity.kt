package com.darkphoenix.sannadhasena

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darkphoenix.sannadhasena.databinding.ActivityMainBinding
import com.darkphoenix.sannadhasena.utils.NetworkUtils
import com.darkphoenix.sannadhasena.utils.PrefsManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        PrefsManager.init(applicationContext)
        NetworkUtils.init(applicationContext,findViewById(android.R.id.content))
        NetworkUtils.registerNetworkCallback()
        setContentView(view)
    }
}