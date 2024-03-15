package com.skitracking.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class LocationReceiver @Inject constructor(): BroadcastReceiver() {

    private var _locationFlow = MutableStateFlow(BroadcastLocation())
    val locationFlow: Flow<BroadcastLocation> = _locationFlow


    fun setLocation(
        lat: String?,
        long: String?,
    ) {

    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "TEST_ACTION"){
            val lat = intent.getStringExtra("lat")
            val long = intent.getStringExtra("long")

            _locationFlow = MutableStateFlow(BroadcastLocation(lat, long))

            println("Received test intent: lat : $lat, long : $long")
        }
    }



}


data class BroadcastLocation(
    val lat: String? = null,
    val long: String? = null,
)