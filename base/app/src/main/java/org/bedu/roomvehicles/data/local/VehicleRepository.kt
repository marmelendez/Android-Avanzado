package org.bedu.roomvehicles.data.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VehicleRepository(
    private val vehicleDao: VehicleDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

) {
    fun getVehicles(): List<Vehicle> {
        return vehicleDao.getVehicles()
    }

    suspend fun populateVehicles(vehicles: List<Vehicle>) = withContext(ioDispatcher){
        return@withContext vehicleDao.insertAll(vehicles)
    }
}