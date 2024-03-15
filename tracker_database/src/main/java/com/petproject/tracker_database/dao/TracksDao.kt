package com.petproject.tracker_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petproject.tracker_database.models.TrackRoomEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TracksDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: TrackRoomEntity)

    @Delete
    suspend fun deleteTrack(track: TrackRoomEntity)


    @Query("SELECT * FROM tracks_table ORDER BY timeStamp DESC")
    fun getAllTracksSortedByDate(): Flow<List<TrackRoomEntity>>

    // You may adding another get all data by another parameter

    @Query("SELECT SUM(timeInMillis) FROM tracks_table")
     fun getTotalTimeInMillis(): Flow<Long>

    @Query("SELECT SUM(destinationInMeters) FROM tracks_table")
    fun getTotalDestinationInMeters(): Flow<Long>

    //@Query("SELECT AVG() FROM tracks_table")
   // fun getTotalAvgSpeed(): Flow<Float>

}