package com.example.crud_vehicle

import android.content.Context

class Pref(val context : Context) {

    // Claves de los métodos para SharedPreference
    private val SHARED_NAME = "SharedData"
    private val SHARED_STRING = "string"
    private val SHARED_BOOLEAN = "boolean"

    // Variable que inicializa SharedPreference
    val storange = context.getSharedPreferences(SHARED_NAME, 0)

    /* :: Métodos para cargar los diferentes tipos de datos :: */
    fun saveString(valueString : String){
        storange.edit().putString(SHARED_STRING, valueString).apply()
    }

    fun saveBoolean(valueBoolean : Boolean){
        storange.edit().putBoolean(SHARED_BOOLEAN, valueBoolean).apply()
    }

    /* :: Métodos para recuperar los datos del SharedPreference :: */
    fun getString() : String{
        return storange.getString(SHARED_STRING, "")!!
    }

    fun getBoolean() : Boolean{
        return storange.getBoolean(SHARED_BOOLEAN, false)
    }

    /*  :: Método para limpiar el SharedPreference :: */
    fun clear(){
        return storange.edit().clear().apply()
    }

    fun clearType(){
        return storange.edit().remove(SHARED_STRING).apply()
    }
}