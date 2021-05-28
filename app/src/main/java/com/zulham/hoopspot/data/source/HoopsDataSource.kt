package com.zulham.hoopspot.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.vo.Resource

interface HoopsDataSource {
    fun getParking() : LiveData<Resource<PagedList<ParkingResponse>>>
    fun getDetailParking(parking_id : String) : LiveData<Resource<ParkingResponse>>

    fun getPlace() : LiveData<Resource<PagedList<PlaceResponse>>>
    fun getDetailPlace(place_id : String) : LiveData<Resource<PlaceResponse>>
}