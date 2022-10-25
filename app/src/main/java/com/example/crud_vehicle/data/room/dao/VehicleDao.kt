package com.example.crud_vehicle.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crud_vehicle.data.room.entities.Vehicle

/** :: Definimos los métodos para interactuar con la base de datos :: **/
@Dao
interface VehicleDao {

    // Vamos a definir una consulta de SQL para traer todos los registros de la tabla
    @Query("SELECT * FROM vehicle_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Vehicle>>

    // Buscamos por id
    @Query("SELECT * FROM vehicle_data WHERE id=:id")
    fun getVehicleById(id: Int): LiveData<Vehicle>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVehicle(vehicle: Vehicle)

    @Update
    suspend fun updateVehicle(vehicle: Vehicle)

    @Delete
    suspend fun deleteVehicle(vehicle: Vehicle)

    @Query("DELETE FROM vehicle_data")
    suspend fun deleteAllVehicles()
}