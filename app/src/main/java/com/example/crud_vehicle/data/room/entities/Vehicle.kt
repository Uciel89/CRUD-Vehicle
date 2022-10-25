package com.example.crud_vehicle.data.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/* :: Creamos una tabla con sus columnas :: */
@Parcelize
@Entity(tableName = "vehicle_data")
data class Vehicle (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val marca: String,
    val modelo: String,
    val patente: String,
    val motor: String,
    val color: String,
    val puertas: String
): Parcelable