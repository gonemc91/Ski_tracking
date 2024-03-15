package com.skitracking.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skitracking.receiver.BroadcastLocation
import com.skitracking.receiver.LocationReceiver
import com.skitracking.utils.Container
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    locationReceiver: LocationReceiver
): ViewModel() {

    private val _location: MutableStateFlow<Container<BroadcastLocation>> =
        MutableStateFlow(Container.Pending)

    private val _saveInProgress = MutableStateFlow(false)


    val flowScreenState: Flow<Container<State>> = combine(
        _location,
        locationReceiver.locationFlow,
        _saveInProgress,
        ::mergeSources
    )



    fun getScreenState(): ScreenState{
        val screenState = ScreenState()
        viewModelScope.launch {
            flowScreenState.collect { container ->
                Log.d("AAAA", "${container}")
                val location = container.getOrNull()

                screenState.copy(
                    long = location?.long.toString(),
                    lat = location?.lat.toString(),
                )
            }
        }
        return screenState
    }


    private fun mergeSources(
        location: Container<BroadcastLocation>,
        broadcastLocation: BroadcastLocation,
        saveInProgress: Boolean,
    ): Container<State> {
        return location.map { resultLocation ->
            State(
                locationBroadcast = broadcastLocation,
                showSaveButton = saveInProgress
            )
        }
    }
}


//var location: StateFlow<BroadcastLocation>? = null


class State(
    locationBroadcast: BroadcastLocation,
    private val showSaveButton: Boolean
) {
    val long: String? = locationBroadcast.long
    val lat: String? = locationBroadcast.lat
}