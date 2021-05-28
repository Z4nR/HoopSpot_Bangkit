package com.zulham.hoopspot.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zulham.hoopspot.api.ApiConfig
import com.zulham.hoopspot.api.ApiResponse
import com.zulham.hoopspot.data.local.LocalDataSource
import com.zulham.hoopspot.data.remote.response.ParkingResponse
import com.zulham.hoopspot.data.remote.response.PlaceResponse
import com.zulham.hoopspot.utils.AppExecutors
import com.zulham.hoopspot.vo.NetworkBoundResource
import com.zulham.hoopspot.vo.Resource

class HoopsRepository (
    private val apiConfig: ApiConfig,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : HoopsDataSource {

    companion object{
        @Volatile
        private var instance: HoopsRepository? =null

        fun getInstance(apiConfig: ApiConfig, localDataSource: LocalDataSource, appExecutors: AppExecutors): HoopsRepository =
            instance?: synchronized(this){
                instance?: HoopsRepository(apiConfig, localDataSource, appExecutors)
            }
    }

    override fun getParking(): LiveData<Resource<PagedList<ParkingResponse>>> {
        return object: NetworkBoundResource<PagedList<ParkingResponse>, List<ParkingResponse>>(appExecutors){

            public override fun loadFromDB(): LiveData<PagedList<ParkingResponse>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getParking(), config).build()
            }


            override fun shouldFetch(data: PagedList<ParkingResponse>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ParkingResponse>>> = apiConfig.getParking()

            override fun saveCallResult(data: List<ParkingResponse>) {
                val parkingList = ArrayList<ParkingResponse>()
                for (response in data){
                    val movie = ParkingResponse(response.id,
                        response.title,
                        response.description,
                        response.tanggal,
                        response.photo)
                    parkingList.add(movie)
                }
                localDataSource.insertParking(parkingList)
            }

        }.asLiveData()
    }

    override fun getDetailParking(parking_id: String): LiveData<Resource<ParkingResponse>> {
        return object : NetworkBoundResource<ParkingResponse, ParkingResponse>(appExecutors){
            override fun loadFromDB(): LiveData<ParkingResponse> {
                return localDataSource.getParkingById(parking_id)
            }

            override fun shouldFetch(data: ParkingResponse?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<ParkingResponse>> {
                return apiConfig.getDetailParking(parking_id)
            }

            override fun saveCallResult(data: ParkingResponse) {
            }
        }.asLiveData()
    }

    override fun getPlace(): LiveData<Resource<PagedList<PlaceResponse>>> {
        return object: NetworkBoundResource<PagedList<PlaceResponse>, List<PlaceResponse>>(appExecutors){

            public override fun loadFromDB(): LiveData<PagedList<PlaceResponse>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getPlace(), config).build()
            }


            override fun shouldFetch(data: PagedList<PlaceResponse>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<PlaceResponse>>> = apiConfig.getPlace()

            override fun saveCallResult(data: List<PlaceResponse>) {
                val placeList = ArrayList<PlaceResponse>()
                for (response in data){
                    val tvMovie = PlaceResponse(response.id,
                        response.title,
                        response.description,
                        response.rilis,
                        response.photo)
                    placeList.add(tvMovie)
                }
                localDataSource.insertPlace(placeList)
            }

        }.asLiveData()
    }

    override fun getDetailPlace(place_id: String): LiveData<Resource<PlaceResponse>> {
        return object : NetworkBoundResource<PlaceResponse, PlaceResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PlaceResponse> {
                return localDataSource.getPlaceId(place_id)
            }

            override fun shouldFetch(data: PlaceResponse?): Boolean {
                return data == null}

            override fun createCall(): LiveData<ApiResponse<PlaceResponse>> =
                apiConfig.getDetailPlace(place_id)

            override fun saveCallResult(data: PlaceResponse) {
            }

        }.asLiveData()
    }

}

