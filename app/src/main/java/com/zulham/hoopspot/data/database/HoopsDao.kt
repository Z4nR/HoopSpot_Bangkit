package com.zulham.hoopspot.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.remote.response.PlaceResponse

@Dao
interface HoopsDao {
    @Query("SELECT * FROM parkingentities")
    fun getParking(): DataSource.Factory<Int, ParkingResponse>

    @Query("SELECT * FROM placeentities")
    fun getPlace(): DataSource.Factory<Int, PlaceResponse>

    @Query("SELECT * FROM parkingentities WHERE id =:parkingId")
    fun getParkingById(parkingId: String): LiveData<ParkingResponse>

    @Query("SELECT * FROM placeentities WHERE id =:placeId")
    fun getPlaceId(placeId: String): LiveData<PlaceResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertParking(parking : List<ParkingResponse>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlace(place : List<PlaceResponse>)

}