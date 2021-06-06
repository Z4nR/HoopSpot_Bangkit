package com.zulham.hoopspot.data.remote

import androidx.lifecycle.LiveData
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.data.remote.response.ParkingResponse

interface HoopsDataSource {

    fun getPlaceList(): LiveData<List<HoopsEntityItem>>
    fun getParkList(place_id: Int): LiveData<List<HoopsEntityItem>>
    fun getParkDetail(place_id: Int, park_id: Int): LiveData<ParkingResponse>

}