package com.skitracking.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.petproject.location_client.location_service.LocationService
import com.skitracking.receiver.LocationReceiver
import com.skitracking.ui.screens.MainScreen
import com.skitracking.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class ScreenState(
    val long: String = "-------",
    val lat: String = "-------",
) : Parcelable


@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val testReceiver = LocationReceiver()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission(this)

        registerReceiver(
            testReceiver,
            IntentFilter("TEST_ACTION"), RECEIVER_EXPORTED
        )


        setContent {

           val flowScreenState = ScreenState()

            MyApplicationTheme {

                val screenState = viewModel.getScreenState()

                MainScreen(
                    screenState,
                    startIntent = {
                    Intent(applicationContext, LocationService::class.java).apply {
                        action = LocationService.ACTION_START
                        applicationContext.startService(this)
                    }
                }) {
                    Intent(applicationContext, LocationService::class.java).apply {
                        action = LocationService.ACTION_STOP
                        applicationContext.startService(this)
                    }
                }
            }
        }
    }






    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(testReceiver)
    }
}




@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun requestPermission(activity: Activity){
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.POST_NOTIFICATIONS,
        ),
        0
    )
}




