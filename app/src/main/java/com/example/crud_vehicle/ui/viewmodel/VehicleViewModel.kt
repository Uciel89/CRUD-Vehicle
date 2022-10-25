package com.example.crud_vehicle.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crud_vehicle.data.room.VehicleDatabase
import com.example.crud_vehicle.data.repository.VehicleRepository
import com.example.crud_vehicle.data.room.entities.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Vehicle>>
    private val repository: VehicleRepository

    init {
        val vehicleDao = VehicleDatabase.getDatabase(application).vehicleDao()
        repository = VehicleRepository(vehicleDao)
        readAllData = repository.readAllData
    }

    // :: Methods to communication with the data base ::
    // :: Métodos para comunicarse con la base de datos ::
    fun getAllVehicles(): LiveData<List<Vehicle>> {
        return readAllData
    }

    fun getVehicleById(id: Int): LiveData<Vehicle> {
        return repository.getVehicleById(id)
    }

    fun addVehicle(vehicle: Vehicle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVehicle(vehicle)
        }
    }

    fun updateVehicle(vehicle: Vehicle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateVehicle(vehicle)
        }
    }

    fun deleteVehicle(vehicle: Vehicle) {
        viewModelScope.launch(Dispatchers.IO) {
             repository.deleteVehicle(vehicle)
        }
    }

    fun deleteAllVehicles() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllVehicles()
        }
    }
}