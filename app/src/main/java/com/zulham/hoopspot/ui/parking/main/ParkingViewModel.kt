package com.zulham.hoopspot.ui.parking.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.remote.HoopsRepository
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem

class ParkingViewModel(private val repository: HoopsRepository) : ViewModel() {

    private lateinit var detailPlace: LiveData<List<HoopsEntityItem>>

    fun setPlaceDetail(place_id : Int){
        detailPlace = repository.getParkList(place_id)
    }

    fun getParkList(): LiveData<List<HoopsEntityItem>> {
        return detailPlace
    }

}