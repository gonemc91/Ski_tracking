package com.petproject.tracker_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.petproject.tracker_database.dao.TracksDao
import com.petproject.tracker_database.models.TrackRoomEntity


class TrackerDatabase internal constructor(private val database: TrackerRoomDataBase){
    val articlesDao: TracksDao
        get() = database.getTracksDao()
}

@Database(version = 1, entities = [TrackRoomEntity::class])
internal abstract class TrackerRoomDataBase: RoomDatabase() {
     abstract fun getTracksDao(): TracksDao
}

fun trackerDatabase(applicationContext: Context): TrackerDatabase {
    val trackerRoomDatabase =  Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        TrackerRoomDataBase::class.java,
        "news_database"
    ).build()
    return TrackerDatabase(trackerRoomDatabase)
}