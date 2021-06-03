package com.zulham.hoopspot.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.data.remote.response.ParkingItem

class HoopsRepository private constructor(private val remoteDataSource: RemoteDataSource): HoopsDataSource{

    companion object{
        @Volatile
        private var instance: HoopsRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource): HoopsRepository =
            instance ?: synchronized(this){
                instance ?: HoopsRepository(remoteDataSource)
            }
    }

    override fun getPlaceList(): LiveData<List<HoopsEntityItem>> {
        val place = MutableLiveData<List<HoopsEntityItem>>()
        remoteDataSource.getPlaceList(object : RemoteDataSource.LoadListPlaceCallback{
            override fun onAllListPlaceReceive(resultsItem: List<HoopsEntityItem>) {
                place.postValue(resultsItem)
            }
        })
        return place
    }

    override fun getParkList(place_id: Int): LiveData<List<ParkingItem>> {
        val park = MutableLiveData<List<ParkingItem>>()
        remoteDataSource.getParkList(place_id, object : RemoteDataSource.LoadListParkCallback{
            override fun onAllListParkReceive(resultsItem: List<ParkingItem>) {
                park.postValue(resultsItem)
            }
        })
        return park
    }

    override fun getParkDetail(park_id: Int): LiveData<ParkingItem> {
        val detailPark = MutableLiveData<ParkingItem>()
        remoteDataSource.getParkDetail(park_id, object : RemoteDataSource.LoadDetailCallback{
            override fun onDetailReceive(detailItem: ParkingItem) {
                detailPark.postValue(detailItem)
            }
        })
        return detailPark
    }

}