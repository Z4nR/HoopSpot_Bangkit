package com.zulham.hoopspot.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.data.remote.response.ParkingResponse

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

    override fun getParkList(place_id: Int): LiveData<List<HoopsEntityItem>> {
        val park = MutableLiveData<List<HoopsEntityItem>>()
        remoteDataSource.getParkList(place_id, object : RemoteDataSource.LoadListParkCallback{
            override fun onAllListParkReceive(resultsItem: List<HoopsEntityItem>) {
                park.postValue(resultsItem)
            }
        })
        return park
    }

    override fun getParkDetail(place_id: Int, park_id: Int): LiveData<ParkingResponse> {
        val detailPark = MutableLiveData<ParkingResponse>()
        remoteDataSource.getParkDetail(place_id, park_id, object : RemoteDataSource.LoadDetailCallback{
            override fun onDetailReceive(detailItem: ParkingResponse) {
                detailPark.postValue(detailItem)
            }
        })
        return detailPark
    }

}