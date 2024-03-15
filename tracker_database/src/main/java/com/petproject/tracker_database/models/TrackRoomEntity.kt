package com.petproject.tracker_database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tracks_table")

data class TrackRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val maxSpeedInKMH: String,
    val destinationInMeters: String,
    val timeStamp: Long,
    val timeInMillis: Long,
    val avgSpeedInKMH: Float
)
