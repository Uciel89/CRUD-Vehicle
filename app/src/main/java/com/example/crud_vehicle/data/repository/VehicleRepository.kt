package com.example.crud_vehicle.data.repository

import androidx.lifecycle.LiveData
import com.example.crud_vehicle.data.room.dao.VehicleDao
import com.example.crud_vehicle.data.room.entities.Vehicle

class VehicleRepository (private val vehicleDao: VehicleDao) {

    val readAllData: LiveData<List<Vehicle>> = vehicleDao.readAllData()

    fun getVehicleById(id: Int): LiveData<Vehicle> {
        return vehicleDao.getVehicleById(id)
    }

    suspend fun addVehicle(vehicle: Vehicle) {
        vehicleDao.addVehicle(vehicle)
    }

    suspend fun updateVehicle(vehicle: Vehicle) {
        vehicleDao.updateVehicle(vehicle)
    }

    suspend fun deleteVehicle(vehicle: Vehicle) {
        vehicleDao.deleteVehicle(vehicle)
    }

    suspend fun deleteAllVehicles() {
        vehicleDao.deleteAllVehicles()
    }
}