package com.example.crud_vehicle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crud_vehicle.model.Vehicle

@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao

    companion object {
        @Volatile
        private var INSTANCE: VehicleDatabase? = null

        // Función para comprobar si ya hay una instancia de la base de datos dentro del sistema
        fun getDatabase(context: Context): VehicleDatabase {
            val tempInstance = INSTANCE
            // Si ya hay una instancia creada, la devolvemos
            if(tempInstance != null) {
                return tempInstance
            }

            // Si no la creamos
            synchronized(this) {
                // Generamos una nueva base de datos
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VehicleDatabase::class.java,
                    "vehicle_database"
                ).build()

                // Pasamos está nueva base de datos a nuestra constante
                INSTANCE = instance

                // La retornamos
                return instance
            }
        }
    }
}