package com.example.traveler

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.domain.model.STATE
import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(format: String): String {
    //simple date formatter
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

    //return the formatted date string
    return dateFormatter.format(this)
}

fun getStateColor(context: Context, state: STATE) : Int {
    return when (state) {
        STATE.PREPARING -> ContextCompat.getColor(context, R.color.trip_prepare)
        STATE.TRAVELING -> ContextCompat.getColor(context, R.color.trip_ing)
        STATE.DONE -> ContextCompat.getColor(context, R.color.trip_done)
    }
}