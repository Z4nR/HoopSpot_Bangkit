package com.zulham.hoopspot.data.remote

import androidx.lifecycle.LiveData
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.data.remote.response.ParkingItem

interface HoopsDataSource {

    fun getPlaceList(): LiveData<List<HoopsEntityItem>>
    fun getParkList(place_id: Int): LiveData<List<ParkingItem>>
    fun getParkDetail(park_id: Int): LiveData<ParkingItem>

}