package com.zulham.hoopspot.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.remote.response.ParkingResult
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.data.remote.response.PlaceResult
import com.zulham.hoopspot.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiConfig {
    companion object{
        @Volatile
        private var instance: ApiConfig?= null

        fun getInstance(): ApiConfig=
            instance?: synchronized(this){
                instance?: ApiConfig()
            }
    }
    fun getParking() : LiveData<ApiResponse<List<ParkingResponse>>> {
        EspressoIdlingResource.increment()
        val parking = MutableLiveData<ApiResponse<List<ParkingResponse>>>()
        val call = ApiService.service.getMovie("afc1213e84fb637e510e20542b9ab3da",1)
        try {
            call.enqueue(object : Callback<ParkingResult> {
                override fun onResponse(call: Call<ParkingResult>, response: Response<ParkingResult>) {
                    if (response.isSuccessful) {
                        val responses = response.body()?.let { ApiResponse.success(it.result) }
                        parking.postValue(responses!!)
                        EspressoIdlingResource.decrement()
                    }
                }
                override fun onFailure(call: Call<ParkingResult>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                    EspressoIdlingResource.decrement()
                }
            })
        }catch (e: Exception){
            e.printStackTrace()
        }
        return parking
    }

    fun getDetailParking(id: String): LiveData<ApiResponse<ParkingResponse>> {
        EspressoIdlingResource.increment()
        val detailParking = MutableLiveData<ApiResponse<ParkingResponse>>()
        val call = ApiService.service.getDetailMovie(id,"afc1213e84fb637e510e20542b9ab3da")
        try {
            call.enqueue(object : Callback<ParkingResponse> {
                override fun onResponse(call: Call<ParkingResponse>, response: Response<ParkingResponse>) {
                    if (response.isSuccessful){
                        val responses = response.body()?.let { ApiResponse.success(it) }
                        detailParking.postValue(responses!!)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<ParkingResponse>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                    EspressoIdlingResource.decrement()
                }
            })
        }catch (e: Exception){
            e.printStackTrace()
        }
        return detailParking
    }

    fun getPlace() : LiveData<ApiResponse<List<PlaceResponse>>> {
        EspressoIdlingResource.increment()
        val place = MutableLiveData<ApiResponse<List<PlaceResponse>>>()
        val call = ApiService.service.getTvMovie("afc1213e84fb637e510e20542b9ab3da",1)
        try {
            call.enqueue(object : Callback<PlaceResult> {
                override fun onResponse(call: Call<PlaceResult>, response: Response<PlaceResult>) {
                    if (response.isSuccessful) {
                        val responses = response.body()?.let { ApiResponse.success(it.result) }
                        place.postValue(responses!!)
                        EspressoIdlingResource.decrement()
                    }
                }
                override fun onFailure(call: Call<PlaceResult>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                    EspressoIdlingResource.decrement()
                }
            })
        }catch (e: Exception){
            e.printStackTrace()
        }
        return place
    }

    fun getDetailPlace(id: String): LiveData<ApiResponse<PlaceResponse>> {
        EspressoIdlingResource.increment()
        val detailPlace = MutableLiveData<ApiResponse<PlaceResponse>>()
        val call = ApiService.service.getDetailTvMovie(id,"afc1213e84fb637e510e20542b9ab3da")
        try {
            call.enqueue(object : Callback<PlaceResponse> {
                override fun onResponse(
                    call: Call<PlaceResponse>,
                    response: Response<PlaceResponse>
                ) {
                    if (response.isSuccessful) {
                        val responses = response.body()?.let { ApiResponse.success(it) }
                        detailPlace.postValue(responses!!)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<PlaceResponse>?, t: Throwable?) {
                    Log.d("Failed", t?.message.toString())
                    EspressoIdlingResource.decrement()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return detailPlace
    }
}