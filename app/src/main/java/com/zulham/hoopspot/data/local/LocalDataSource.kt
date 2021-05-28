package com.zulham.hoopspot.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.zulham.hoopspot.data.database.HoopsDao
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.remote.response.PlaceResponse

class LocalDataSource private constructor(private val mHoopsDao : HoopsDao){

    companion object{
        private var INSTANCE :LocalDataSource? = null

        fun getInstance(hoopDao: HoopsDao): LocalDataSource {
            if(INSTANCE == null){
                INSTANCE = LocalDataSource(hoopDao)
            }
            return INSTANCE as LocalDataSource
        }
    }
    fun getParking(): DataSource.Factory<Int, ParkingResponse> = mHoopsDao.getParking()

    fun getParkingById(parkingId: String): LiveData<ParkingResponse> = mHoopsDao.getParkingById(parkingId)

    fun getPlace(): DataSource.Factory<Int,PlaceResponse> = mHoopsDao.getPlace()

    fun getPlaceId(placeId: String): LiveData<PlaceResponse> = mHoopsDao.getPlaceId(placeId)

    fun insertParking(parking: List<ParkingResponse>) = mHoopsDao.insertParking(parking)

    fun insertPlace(place: List<PlaceResponse>) = mHoopsDao.insertPlace(place)

}