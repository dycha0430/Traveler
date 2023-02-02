package com.example.traveler.ui.home

import android.content.Context
import android.location.Geocoder
import android.widget.Toast
import com.example.traveler.R
import com.example.traveler.model.Destination
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import java.util.*

class MapController constructor(private val context: Context, private val destination: Destination) : OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private var geocoder = Geocoder(context, Locale.getDefault())
    private val zoom: Float = 5F

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
        val latLng = getLatLngFromName(destination.name)
        if (latLng != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom*2))
        }
    }

    fun moveMap(address: String) {
        val latLng = getLatLngFromName(address)
        if (latLng == null) {
            Toast.makeText(context, context.getString(R.string.error_find_location), Toast.LENGTH_SHORT).show()
            return
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom*3))
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
                ret = if (list.isEmpty()) null
                else list[0].getAddressLine(0)
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
                ret = if (list.isEmpty()) null
                else LatLng(list[0].latitude, list[0].longitude)
            }
        }

        return ret
    }
}