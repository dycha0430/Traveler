package com.example.traveler.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel>(
    val bindingFactory: (LayoutInflater) -> VB
) : AppCompatActivity() {
    private var _binding: VB? = null
    val binding get() = _binding!!
    protected abstract val viewModel: VM
    protected abstract fun observeUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)

        observeUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}