package com.zulham.hoopspot.api

import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.remote.response.ParkingResult
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.data.remote.response.PlaceResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class ApiService {
    companion object{
        private val retrofit: Retrofit by lazy{
            Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        val service by lazy {
            val create: ApiServices = retrofit.create(ApiServices::class.java)
            create
        }
    }
}

interface ApiServices {
    @GET("movie/popular")
    fun getMovie(
            @Query("api_key") api_key: String,
            @Query("page") page: Int
    ): Call<ParkingResult>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
            @Path("movie_id") id: String,
            @Query("api_key") api_key: String
    ): Call<ParkingResponse>

    @GET("tv/popular")
    fun getTvMovie(
            @Query("api_key") api_key: String,
            @Query("page") page: Int
    ): Call<PlaceResult>

    @GET("tv/{tv_id}")
    fun getDetailTvMovie(
            @Path("tv_id") tv_id: String,
            @Query("api_key") api_key: String
    ): Call<PlaceResponse>
}