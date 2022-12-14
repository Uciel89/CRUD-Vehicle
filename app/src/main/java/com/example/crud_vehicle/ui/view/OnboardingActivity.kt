package com.example.crud_vehicle.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.viewpager2.widget.ViewPager2
import com.example.crud_vehicle.R
import com.example.crud_vehicle.SharedApp
import com.example.crud_vehicle.data.model.OnboardingItem
import com.example.crud_vehicle.ui.viewmodel.OnboardingItemAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnboardingActivity : AppCompatActivity() {

    // :: Onboarding Adapter ::
    private lateinit var onboardingItemAdapter: OnboardingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setOnboardingItems()
        checkPref()
    }

    // :: Method to setter data in the ViewPager ::
    // :: Método para setear datos en el ViewPager ::
    private fun setOnboardingItems() {
        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.raw.list,
                    title = "Listado de vehículos",
                    description = "Mediante una simple interfaz, poder tener a la palma de la mano un listado de tus vehículos a la venta"
                ),
                OnboardingItem(
                    onboardingImage = R.raw.car,
                    title = "Visualización de características",
                    description = "Visualizar de manera sencilla las características de dichos vehículos"
                ),
            )
        )
        // :: Config WormDotsIndicator ::
        // :: Configuración WormDotsIndicator ::
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)

        onboardingViewPager.adapter = onboardingItemAdapter
        dotsIndicator.attachTo(onboardingViewPager)

        // :: Configuring to the button to navigate in the ViewPagers ::
        // :: Configuración para el botón para navegar en las ViewPagers ::
        findViewById<FloatingActionButton>(R.id.buttonMovePage).setOnClickListener {
            if(onboardingViewPager.currentItem + 1 < onboardingItemAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                checkBoxShared()
            }
        }

        findViewById<MaterialButton>(R.id.buttonSkip).setOnClickListener {
            checkBoxShared()
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // :: SharedPreference config ::
    private fun checkBoxShared(){
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        if(checkBox.isChecked){
            SharedApp.pref.saveBoolean(checkBox.isChecked)
            navigateToMain()
        }
        else{ navigateToMain() }
    }

    private fun checkPref(){
        if(SharedApp.pref.getBoolean()){
            navigateToMain()
        }
    }
}