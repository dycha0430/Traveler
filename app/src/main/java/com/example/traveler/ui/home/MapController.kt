package com.example.traveler.ui.home

import android.content.Context
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class MapController constructor(context: Context) : OnMapReadyCallback {
    private val geocoder = Geocoder(context)
    private val zoom: Float = 8.0F

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onMapReady(googleMap: GoogleMap) {
//        val location = getLatLngFromName("인천광역시 남동구 구월동")
        val location = LatLng(37.413294, 127.269311)
        if (location != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoom))
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getNameFromLatLng(latitude: Double, longitude: Double): String? {
        var ret: String? = null
        geocoder.getFromLocation(
            latitude, longitude, 1,
            Geocoder.GeocodeListener { addresses ->
                if (addresses.isNotEmpty()) {
                    ret = addresses[0].getAddressLine(0)
                }
            }
        )
        return ret
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getLatLngFromName(name: String): LatLng? {
        var ret: LatLng? = null
        geocoder.getFromLocationName(
            name, 1,
            Geocoder.GeocodeListener { addresses ->
                if (addresses.isNotEmpty()) {
                    ret = LatLng(addresses[0].latitude, addresses[0].longitude)
                }
            }
        )
        return ret
    }
}