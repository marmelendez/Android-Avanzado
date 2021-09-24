package org.bedu.roomvehicles.data.local

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class VehicleRepository(
    private val vehicleDao: VehicleDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

) {
    fun getVehicles(): LiveData<List<Vehicle>> {
        return vehicleDao.getVehicles()
    }

    suspend fun populateVehicles(vehicles: List<Vehicle>) = withContext(ioDispatcher){
        return@withContext vehicleDao.insertAll(vehicles)
    }

    suspend fun removeVehicle(vehicle: Vehicle) {
        coroutineScope {
            launch{vehicleDao.removeVehicle(vehicle)}
        }
    }
}