package com.zulham.hoopspot.ui.parking.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.source.HoopsRepository
import com.zulham.hoopspot.vo.Resource

//class ParkingViewModel : ViewModel() {
//    private val detailPlace = MutableLiveData<HoopsEntity>()
//    private val detailPark = MutableLiveData<List<ParkingArea>?>()
//
//    fun setPlaceDetail(park: HoopsEntity){
//        detailPlace.postValue(park)
//    }
//
//    fun setParkList(park: List<ParkingArea>?){
//        detailPark.postValue(park)
//    }
//
//    fun getDetail(): LiveData<List<ParkingArea>?> {
//        return detailPark
//    }
//
//}
class ParkingViewModel(private val repository: HoopsRepository) : ViewModel(){
    fun getParking() : LiveData<Resource<PagedList<ParkingResponse>>> = repository.getParking()
}