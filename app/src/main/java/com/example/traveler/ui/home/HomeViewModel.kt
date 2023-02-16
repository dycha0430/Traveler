package com.example.traveler.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.traveler.base.BaseViewModel
import com.example.traveler.base.Event
import com.example.traveler.model.Place
import com.example.traveler.model.Schedule
import com.example.traveler.model.TripPlan
import com.example.traveler.usecase.GetAllTripPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTripPlansUseCase: GetAllTripPlansUseCase
) : BaseViewModel() {
    private val _tripPlans = MutableLiveData<ArrayList<TripPlan>>()
    val tripPlans: LiveData<ArrayList<TripPlan>> = _tripPlans

    private val _selectedTripPlan = MutableLiveData<TripPlan>()
    val selectedTripPlan: LiveData<TripPlan> = _selectedTripPlan

    private val _selectedPlace = MutableLiveData<Place>()
    val selectedPlace: LiveData<Place> = _selectedPlace

    private val _selectedSchedule = MutableLiveData<Schedule>()
    val selectedSchedule: LiveData<Schedule> = _selectedSchedule

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

    fun clickTripPlan(tripPlanIdx: Int) {
        _selectedTripPlan.value = tripPlans.value?.get(tripPlanIdx)
    }

    fun clickPlace(name: String, address: String) {
        _selectedPlace.value = Place(name, address)
    }

    fun clickSchedule(schedule: Schedule) {
        _selectedSchedule.value = schedule
    }
}