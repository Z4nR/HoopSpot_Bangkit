package com.zulham.hoopspot.utils

import com.zulham.hoopspot.data.HoopsEntity
import com.zulham.hoopspot.data.ParkingArea
import com.zulham.hoopspot.data.ParkingDetail

object DataDummy {

    fun generateDummyPlace(): ArrayList<HoopsEntity>{

        val place = ArrayList<HoopsEntity>()

        place += HoopsEntity(
            1,
            "Place A",
            null,
            "Road 123",
            listOf(ParkingArea(
                1,
                "park A",
                null,
                "Road 123 Point 4.5",
                "Empty",
                listOf(ParkingDetail(
                    "Maps",
                    "Image"
                ))
            ),
            ParkingArea(
                2,
                "park B",
                null,
                "Road 123 Point 2.9",
                "Full",
                listOf(ParkingDetail(
                    "Maps",
                    "Image"
                ))
            ))
        )

        place += HoopsEntity(
            2,
            "Place B",
            null,
            "Road ABC",
            listOf(ParkingArea(
                1,
                "park A",
                null,
                "Road ABC Point W.Y",
                "Busy",
                listOf(ParkingDetail(
                    "Maps",
                    "Image"
                ))
            ),
                ParkingArea(
                    2,
                    "park B",
                    null,
                    "Road ABC Point X.T",
                    "Full",
                    listOf(ParkingDetail(
                        "Maps",
                        "Image"
                    ))
                )) )

        return place

    }

}