package com.example.traveler.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.traveler.base.BaseViewModel
import com.example.traveler.base.Event
import com.example.domain.model.Place
import com.example.domain.model.Schedule
import com.example.domain.model.TripPlan
import com.example.domain.usecase.CreateTripPlanUseCase
import com.example.domain.usecase.GetAllTripPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTripPlansUseCase: GetAllTripPlansUseCase,
    private val createTripPlanUseCase: CreateTripPlanUseCase
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

    fun postSchedule(schedule: Schedule) {
        viewModelScope.launch {

        }
    }

    fun createTripPlan(tripPlan: TripPlan) {
        _loadingEvent.value = Event(false)
        viewModelScope.launch {
            createTripPlanUseCase(tripPlan)
        }
    }
}