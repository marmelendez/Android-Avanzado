package org.bedu.roomvehicles.utils

import org.bedu.roomvehicles.data.local.Vehicle
import org.junit.Assert.*
import org.junit.Test

class VehicleUtilsKtTest {
    @Test
    fun getNumberOfVehicles_empty_returnsZero() {
        //GIVEN
        val vehicle = listOf<Vehicle>()

        //WHEN
        val result = getNumberOfVehicles(vehicle)

        //THEN
        assertEquals(0,result)
    }

    @Test
    fun getNumberOfVehicles_null_returnsZero() {
        //GIVEN
        val vehicle = null

        //WHEN
        val result = getNumberOfVehicles(vehicle)

        //THEN
        assertEquals(0,result)
    }
}