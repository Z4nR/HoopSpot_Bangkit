package com.zulham.hoopspot.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HoopsEntity(
    val placeId : Int? = null,
    val placeName : String? = null,
    val placeImg : String? = null,
    val placeAddress : String? = null,
    val placeParkArea : List<ParkingArea>? = null
) : Parcelable

@Parcelize
data class ParkingArea (
    val parkId : Int? = null,
    val parkName : String? = null,
    val parkImg : String? = null,
    val parkAddress : String? = null,
    val parkStatus : String? = null,
    val parkDetail : List<ParkingDetail>? = null
) : Parcelable

@Parcelize
data class ParkingDetail (
    val parkingLocation : String? = null,
    val parkingSpace : String? = null
) : Parcelable
