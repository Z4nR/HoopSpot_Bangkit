package com.zulham.hoopspot.data.remote.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "placeentities")
@Parcelize
data class PlaceResponse (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    var id : String,

    @ColumnInfo(name = "original_name")
    @field:SerializedName("original_name")
    var title : String,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    var description : String,

    @ColumnInfo(name = "first_air_date")
    @field:SerializedName("first_air_date")
    var rilis : String,

    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path")
    var photo : String,

) : Parcelable

data class PlaceResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val total_result: Int,
    @SerializedName("total_pages")
    val total_page:Int,
    @SerializedName("results")
    val result: List<PlaceResponse> = mutableListOf()
)
