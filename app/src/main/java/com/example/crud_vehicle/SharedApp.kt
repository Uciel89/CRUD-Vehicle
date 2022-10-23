package com.example.crud_vehicle

import android.annotation.SuppressLint
import android.app.Application

class SharedApp : Application()  {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var pref: Pref
    }

    override fun onCreate() {
        super.onCreate()

        pref = Pref(applicationContext)

    }
}