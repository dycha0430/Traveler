package com.example.traveler

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.traveler.databinding.MainActivityBinding
import com.example.traveler.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: MainActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, HomeFragment())
                .commitNow()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showProgressBar() {
        binding.loadingBar.visibility = View.VISIBLE
        binding.layoutLoadingBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.loadingBar.visibility = View.GONE
        binding.layoutLoadingBar.visibility = View.GONE
    }
}