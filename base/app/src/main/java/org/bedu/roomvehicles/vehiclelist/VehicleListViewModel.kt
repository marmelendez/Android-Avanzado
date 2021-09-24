package org.bedu.roomvehicles.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.roomvehicles.data.local.Vehicle
import org.bedu.roomvehicles.data.local.VehicleRepository

class VehicleListViewModel(
    private val vehicleRepository: VehicleRepository): ViewModel() {
    private var editVehicleId = MutableLiveData<Int?>()
    val eventEditVehicle = editVehicleId
    //private var vehicles: List<Vehicle> = listOf()
    var vehicles = vehicleRepository.getVehicles()

    fun getVehicleList(): LiveData<List<Vehicle>> {
        vehicles = vehicleRepository.getVehicles()
        return vehicles
    }

    fun removeVehicle(vehicle: Vehicle) = viewModelScope.launch {
        vehicleRepository.removeVehicle(vehicle)
    }

    fun onEdit(vehicleId: Int){
        eventEditVehicle.value = vehicleId
    }

    init {
        prepopulate()
    }

    fun prepopulate() {
        val vehicles = listOf(
            Vehicle(model = "Vento", brand = "Volkswagen", platesNumber = "abc123", isWorking = true),
            Vehicle(model = "Tsuru", brand = "Nissan", platesNumber = "def123", isWorking = true),
            Vehicle(model = "Cx9", brand = "Mazda", platesNumber = "ghi123", isWorking = true),
            Vehicle(model = "Ibiza", brand = "Seat", platesNumber = "jkl123", isWorking = true)
        )

        //poner corutina
        viewModelScope.launch {
            vehicleRepository.populateVehicles(vehicles)
        }
    }



}