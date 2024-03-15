package com.petproject.location_client.location_client

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.petproject.location_client.location_client.exception.LocationException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch


/**
 * Class for getting actually data from gps.
 * @throws [LocationException] if app hasn't location permission or
 * gps is disable.
 * The fun getLocationUpdates return infinite Flow<Location>
 */


internal class DefaultLocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient,
): LocationClient {


    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> {

        hasLocationPermission()
        requestNetworkAndGPSEnable()

        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
            .setWaitForAccurateLocation(false)
            .build()

        return callbackFlow {
            val locationCallback =  object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }
            //using [FusedLocationProviderClient] for getting location

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )
            //blocking coroutine until all data clear, if hi close
            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }
    }
    private fun hasLocationPermission() {
        if (!context.hasLocationPermission()) {
            throw LocationException("Missing location permission")
        }
    }

    private fun requestNetworkAndGPSEnable() {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetWorkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled && !isNetWorkEnabled) {
            throw LocationException("GPS is disabled")
        }
    }
}


