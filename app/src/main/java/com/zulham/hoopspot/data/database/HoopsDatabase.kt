package com.zulham.hoopspot.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.remote.response.PlaceResponse

@Database(entities = [ParkingResponse::class, PlaceResponse::class],
    version = 1,
    exportSchema = false)
abstract class HoopsDatabase : RoomDatabase() {
    abstract fun hoopsDao(): HoopsDao

    companion object{
        @Volatile
        private var INSTANCE: HoopsDatabase? = null

        fun getInstance(context: Context): HoopsDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    HoopsDatabase::class.java,
                    "HoopsEntitiy.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
    }
}