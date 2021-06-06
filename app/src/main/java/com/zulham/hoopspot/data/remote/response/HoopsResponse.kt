package com.zulham.hoopspot.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HoopsResponse(

	@field:SerializedName("hoops_entity")
	val hoopsEntity: List<HoopsEntityItem>? = null

) : Parcelable

@Parcelize
data class HoopsDetailResponse(

	@field:SerializedName("hoops_entity")
	val hoopsEntity: List<HoopsEntityItem>? = null

) : Parcelable

@Parcelize
data class ParkingResponse(

	@field:SerializedName("hoops_entity")
	val hoopsEntity: List<HoopsEntityItem> = arrayListOf()
) : Parcelable

@Parcelize
data class HoopsEntityItem(

	@field:SerializedName("parking")
	val parking: List<ParkingItem>? = null,

	@field:SerializedName("place_name")
	val placeName: String? = null,

	@field:SerializedName("place_address")
	val placeAddress: String? = null,

	@field:SerializedName("place_img")
	val placeImg: String? = null,

	@field:SerializedName("place_id")
	val placeId: Int? = null

) : Parcelable

@Parcelize
data class ParkingItem(

	@field:SerializedName("available_park")
	val availablePark: Int? = null,

	@field:SerializedName("park_img")
	val parkImg: String? = null,

	@field:SerializedName("park_id")
	val parkId: Int? = null,

	@field:SerializedName("park_address")
	val parkAddress: String? = null,

	@field:SerializedName("park_name")
	val parkName: String? = null,

	@field:SerializedName("park_video")
	val parkVideo: String? = null

) : Parcelable
