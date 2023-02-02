package com.example.traveler.ui.home

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import java.util.*

class MapController constructor(private val context: Context) : OnMapReadyCallback {
    private var geocoder = Geocoder(context, Locale.getDefault())
    private val zoom: Float = 15F

    override fun onMapReady(googleMap: GoogleMap) {
        val location = getLatLngFromName("대한민국 인천광역시 남동구 구월동")
        if (location != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom))
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoom))
        }
    }

    private fun getNameFromLatLng(latitude: Double, longitude: Double): String? {
        var ret: String? = null

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(
                latitude, longitude, 1,
                Geocoder.GeocodeListener { addresses ->
                    if (addresses.isNotEmpty()) {
                        ret = addresses[0].getAddressLine(0)
                    }
                }
            )
        } else{
            val list = geocoder.getFromLocation(latitude, longitude, 1)
            if (list != null) {
                if (list.isEmpty()) ret = null
                else ret = list[0].getAddressLine(0)
            }
        }

        return ret
    }

    private fun getLatLngFromName(name: String): LatLng? {
        var ret: LatLng? = null
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocationName(
                name, 1,
                Geocoder.GeocodeListener { addresses ->
                    if (addresses.isNotEmpty()) {
                        ret = LatLng(addresses[0].latitude, addresses[0].longitude)
                    }
                }
            )
        } else {
            val list = geocoder.getFromLocationName(name, 1)

            if (list != null) {
                if (list.isEmpty()) ret = null
                else ret = LatLng(list[0].latitude, list[0].longitude)
            }
        }

        return ret
    }
}