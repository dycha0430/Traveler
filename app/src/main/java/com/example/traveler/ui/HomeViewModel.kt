package com.example.traveler.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.traveler.base.BaseViewModel
import com.example.traveler.base.Event
import com.example.traveler.model.TripPlan
import com.example.traveler.usecase.GetAllTripPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTripPlansUseCase: GetAllTripPlansUseCase
) : BaseViewModel() {
    private val _tripPlans = MutableLiveData<ArrayList<TripPlan>>()
    val tripPlans: LiveData<ArrayList<TripPlan>> = _tripPlans

    fun loadAllTripPlans() {
        _loadingEvent.value = Event(false)
        viewModelScope.launch {
            getAllTripPlansUseCase(Unit).collect {
                val list = ArrayList<TripPlan>()
                list.addAll(it)
                _tripPlans.value = list
            }
        }
    }
}