package com.zulham.hoopspot.api

import com.zulham.hoopspot.data.remote.response.HoopsDetailResponse
import com.zulham.hoopspot.data.remote.response.HoopsResponse
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/")
    fun getPlaceList(): Call<HoopsResponse>

    @GET("/hoops_entity/{place_id}")
    fun getPlaceDetail(
        @Path("place_id") place_id : Int
    ): Call<HoopsDetailResponse>

    @GET("/hoops_entity/{place_id}/parking/{park_id}")
    fun getParkDetail(
        @Path("place_id") place_id : Int,
        @Path("park_id") park_id : Int
    ): Call<ParkingResponse>

}