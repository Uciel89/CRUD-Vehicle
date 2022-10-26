package com.example.crud_vehicle.ui.view.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crud_vehicle.R
import com.example.crud_vehicle.data.room.entities.Vehicle
import com.example.crud_vehicle.ui.viewmodel.VehicleViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddVehicleFragment : Fragment() {

    private lateinit var mVehicleViewModel: VehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_vehicle, container, false)

        // We
        // Instanciamos el viewModel de Vehiculos
        mVehicleViewModel = ViewModelProvider(this)[VehicleViewModel::class.java]

        view.findViewById<MaterialButton>(R.id.buttonSave).setOnClickListener { insertDataToDatabase() }
        return view
    }
    /** :: Method to add data within of the data base :: **/
    /** :: Método para agregar datos dentro de la base de datos :: **/
    private fun insertDataToDatabase() {
        // We get the entered for text input
        // Obtenemos lo ingresado por campos de texto
        val marca = view?.findViewById<TextView>(R.id.txtEditMarca)?.text.toString()
        val modelo = view?.findViewById<TextView>(R.id.txtEditModelo)?.text.toString()
        val patente = view?.findViewById<TextView>(R.id.txtEditPatente)?.text.toString()
        val motor = view?.findViewById<TextView>(R.id.txtEditMotor)?.text.toString()
        val color = view?.findViewById<TextView>(R.id.txtEditColor)?.text.toString()
        val puertas = view?.findViewById<TextView>(R.id.txtEditPuertas)?.text.toString()

        if(inputCheck(marca, modelo, patente, motor, color, puertas)){
            // We create a instance of the Vehicle class
            // Creamos una instancia de la clase Vehiculo
            val vehicle = Vehicle(0,marca, modelo, patente, motor, color, puertas)

            // Add this data into of our data base
            // Agregamos estos datos dentro de nuestra base de datos
            mVehicleViewModel.addVehicle(vehicle)
            Toast.makeText(requireContext(), "Vehiculo guardado correctamente", Toast.LENGTH_SHORT).show()

            // Return to the before fragment
            // Volvemos a al fragment anterior
            findNavController().navigate(R.id.listFragment)
        } else {
            Toast.makeText(requireContext(), "Verificar que todos los campos esten completados", Toast.LENGTH_SHORT).show()
        }
    }

    /** :: Method to verify with the inputs aren´t empty :: **/
    /** :: Método para comprobar que los campos no este vacios :: **/
    private fun inputCheck(
        marca: String,
        modelo: String,
        patente: String,
        motor: String,
        color: String,
        puertas: String
    ): Boolean {
        return (!TextUtils.isEmpty(marca) &&
                !TextUtils.isEmpty(modelo) &&
                !TextUtils.isEmpty(patente) &&
                !TextUtils.isEmpty(motor) &&
                !TextUtils.isEmpty(color) &&
                !TextUtils.isEmpty(puertas))
    }
}