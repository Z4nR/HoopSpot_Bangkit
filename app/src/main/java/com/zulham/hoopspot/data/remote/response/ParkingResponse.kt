package com.zulham.hoopspot.data.remote.response

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "parkingentities")
@Parcelize
data class ParkingResponse (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    var id : String,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    var title : String,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    var description : String,

    @ColumnInfo(name = "release_date")
    @field:SerializedName("release_date")
    var tanggal : String,

    @ColumnInfo(name = "backdrop_path")
    @field:SerializedName("backdrop_path")
    var photo : String,
) : Parcelable

data class ParkingResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val total_result: Int,
    @SerializedName("total_pages")
    val total_page:Int,
    @SerializedName("results")
    val result: List<ParkingResponse> = mutableListOf()
)