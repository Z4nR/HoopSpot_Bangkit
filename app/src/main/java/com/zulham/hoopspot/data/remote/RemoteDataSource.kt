package com.zulham.hoopspot.data.remote

import com.zulham.hoopspot.api.ApiService
import com.zulham.hoopspot.data.remote.response.HoopsDetailResponse
import com.zulham.hoopspot.data.remote.response.HoopsEntityItem
import com.zulham.hoopspot.data.remote.response.HoopsResponse
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val serviceApi: ApiService){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        @InternalCoroutinesApi
        fun getInstance(helper: ApiService): RemoteDataSource =
            instance ?: kotlinx.coroutines.internal.synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getPlaceList(callback: LoadListPlaceCallback){
        serviceApi.getPlaceList().enqueue(object : Callback<HoopsResponse> {
            override fun onResponse(call: Call<HoopsResponse>, response: Response<HoopsResponse>) {
                if (response.isSuccessful){
                    val place = response.body()?.hoopsEntity
                    if (place != null) {
                        callback.onAllListPlaceReceive(place)
                    }
                }
            }

            override fun onFailure(call: Call<HoopsResponse>, t: Throwable) {

            }

        })
    }

    fun getParkList(place_id : Int, callback: LoadListParkCallback){
        serviceApi.getPlaceDetail(place_id).enqueue(object : Callback<HoopsDetailResponse> {
            override fun onResponse(
                call: Call<HoopsDetailResponse>,
                response: Response<HoopsDetailResponse>
            ) {
                if (response.isSuccessful){
                    val park = response.body()?.hoopsEntity
                    if (park != null) {
                        callback.onAllListParkReceive(park)
                    }
                }
            }

            override fun onFailure(call: Call<HoopsDetailResponse>, t: Throwable) {

            }

        })
    }

    fun getParkDetail(place_id: Int, park_id: Int, callback: LoadDetailCallback){
        serviceApi.getParkDetail(place_id, park_id).enqueue(object : Callback<ParkingResponse>{
            override fun onResponse(call: Call<ParkingResponse>, response: Response<ParkingResponse>) {
                if (response.isSuccessful){
                    val parkDetail = response.body()
                    if (parkDetail != null) {
                        callback.onDetailReceive(parkDetail)
                    }
                }
            }

            override fun onFailure(call: Call<ParkingResponse>, t: Throwable) {

            }

        })
    }

    interface LoadListPlaceCallback {
        fun onAllListPlaceReceive(resultsItem: List<HoopsEntityItem>)
    }

    interface LoadListParkCallback {
        fun onAllListParkReceive(resultsItem: List<HoopsEntityItem>)
    }

    interface LoadDetailCallback {
        fun onDetailReceive(detailItem: ParkingResponse)
    }

}
