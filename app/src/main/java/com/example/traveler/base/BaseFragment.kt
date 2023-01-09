package com.example.traveler.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.traveler.MainActivity

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!
    protected abstract val viewModel: VM

    protected abstract fun initUi()
    protected abstract fun observeUi()
    protected abstract fun loadData()
    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        loadData()
        observeUi()
    }

    inline fun <T> observe(liveData: LiveData<T>, crossinline callback: (T) -> Unit) {
        liveData.observe(
            viewLifecycleOwner
        ) { callback(it) }
    }

    inline fun <T> observeEvent(liveData: LiveData<Event<T>>, crossinline callback: (T) -> Unit) {
        liveData.observeEvent(
            viewLifecycleOwner
        ) { callback(it) }
    }

    fun showLoadingBar() {
        (activity as? MainActivity)?.showProgressBar()
    }

    fun hideLoadingBar() {
        (activity as? MainActivity)?.hideProgressBar()
    }
}