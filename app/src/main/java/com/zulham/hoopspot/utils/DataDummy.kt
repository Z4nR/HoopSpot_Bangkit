package com.zulham.hoopspot.utils

import com.zulham.hoopspot.data.HoopsEntity
import com.zulham.hoopspot.data.ParkingArea
import com.zulham.hoopspot.data.ParkingArray

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
                23,
                "Maps",
                listOf(ParkingArray(
                    1,
                    true
                ))
            ),
            ParkingArea(
                2,
                "park B",
                null,
                "Road 123 Point 2.9",
                23,
                "Maps",
                listOf(ParkingArray(
                    1,
                    false
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
                23,
                "Maps",
                listOf(ParkingArray(
                    1,
                    true
                ))
            ),
                ParkingArea(
                    2,
                    "park B",
                    null,
                    "Road ABC Point X.T",
                    23,
                    "Maps",
                    listOf(ParkingArray(
                        1,
                        false
                    ))
                )
            )
        )

        return place

    }

}