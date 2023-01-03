package com.example.traveler.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

open class Event<T> (value: T) {
    var value = value
        private set
    private var isHandled = false

    fun isHandled(): Boolean {
        return if (isHandled) {
            true
        } else {
            isHandled = true
            false
        }
    }
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: Observer<T>) = observe(owner) {
    if (!it.isHandled()) {
        observer.onChanged(it.value)
    }
}