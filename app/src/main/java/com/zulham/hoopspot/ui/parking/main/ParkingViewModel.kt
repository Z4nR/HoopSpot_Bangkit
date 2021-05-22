package com.zulham.hoopspot.ui.parking.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulham.hoopspot.data.HoopsEntity
import com.zulham.hoopspot.data.ParkingArea

class ParkingViewModel : ViewModel() {
    private val detailPlace = MutableLiveData<HoopsEntity>()
    private val detailPark = MutableLiveData<List<ParkingArea>?>()

    fun setPlaceDetail(park: HoopsEntity){
        detailPlace.postValue(park)
    }

    fun setParkList(park: List<ParkingArea>?){
        detailPark.postValue(park)
    }

    fun getDetail(): LiveData<List<ParkingArea>?> {
        return detailPark
    }

}