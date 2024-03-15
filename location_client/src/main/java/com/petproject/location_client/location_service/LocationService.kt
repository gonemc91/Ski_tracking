package com.petproject.location_client.location_service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import com.petproject.location_client.location_client.DefaultLocationClient
import com.petproject.location_client.location_client.LocationClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class LocationService (
): Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var locationClient: LocationClient? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.d("LocationService" , "Location onCreate")
        super.onCreate()
        locationClient = DefaultLocationClient(
           applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("LocationService" , "Location command: ${intent?.action}")
        when(intent?.action){
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(){

        Log.d("LocationService" , "Location service: Start")
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Tracking location...")
            .setContentText("Location: null")
            .setSmallIcon(androidx.core.R.drawable.notification_tile_bg) //TODO ("Change service icon ")
            .setOngoing(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            ?.getLocationUpdates(1000L)
            ?.catch { e->
                e.printStackTrace()
            }
            ?.onEach { location ->
                val lat = location.latitude.toString()
                val long = location.longitude.toString()
                val updatedNotification = notification.setContentText(
                    "Location: ($lat, $long)"
                )


                sendBroadcast(
                   Intent("TEST_ACTION").apply {
                       putExtra("lat", lat)
                       putExtra("long", long)
                   }
                )
                notificationManager.notify(1, updatedNotification.build())
            }
            ?.launchIn(serviceScope)
        startForeground(1, notification.build())

    }


    private fun stop(){
        Log.d("LocationService" , "Location service: Stop")
        stopForeground(STOP_FOREGROUND_REMOVE)
        locationClient = null
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object{
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }

}