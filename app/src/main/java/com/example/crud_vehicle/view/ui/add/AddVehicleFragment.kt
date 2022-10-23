package com.example.crud_vehicle.view.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crud_vehicle.R
import com.example.crud_vehicle.model.Vehicle
import com.example.crud_vehicle.viewmodel.VehicleViewModel
import com.google.android.material.button.MaterialButton

class AddVehicleFragment : Fragment() {

    private lateinit var mVehicleViewModel: VehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_vehicle, container, false)

        // Instanciamos el viewModel de Vehiculos
        mVehicleViewModel = ViewModelProvider(this)[VehicleViewModel::class.java]

        view.findViewById<MaterialButton>(R.id.buttonSave).setOnClickListener { insertDataToDatabase() }
        return view
    }

    /** :: Método para agregar datos dentro de la base de datos :: **/
    private fun insertDataToDatabase() {
        // Obtenemos lo ingresado por campos de texto
        val marca = view?.findViewById<TextView>(R.id.txtEditMarca)?.text.toString()
        val modelo = view?.findViewById<TextView>(R.id.txtEditModelo)?.text.toString()
        val patente = view?.findViewById<TextView>(R.id.txtEditPatente)?.text.toString()
        val motor = view?.findViewById<TextView>(R.id.txtEditMotor)?.text.toString()
        val color = view?.findViewById<TextView>(R.id.txtEditColor)?.text.toString()
        val puertas = view?.findViewById<TextView>(R.id.txtEditPuertas)?.text.toString()

        if(inputCheck(marca, modelo, patente, motor, color, puertas)){
            // Creamos una instancia de la clase Vehiculo
            val vehicle = Vehicle(0,marca, modelo, patente, motor, color, puertas)

            // Agregamos estos datos dentro de nuestra base de datos
            mVehicleViewModel.addVehicle(vehicle)
            Toast.makeText(requireContext(), "Vehiculo guardado correctamente", Toast.LENGTH_SHORT).show()

            // Volvemos a al fragment anterior
            findNavController().navigate(R.id.action_addVehicleFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Verificar que todos los campos esten completados", Toast.LENGTH_SHORT).show()
        }
    }

    /** :: Método para comprobar que los campos no este vacios :: **/
    private fun inputCheck(
        marca: String,
        modelo: String,
        patente: String,
        motor: String,
        color: String,
        puertas: String
    ): Boolean {
        return !(TextUtils.isEmpty(marca) &&
                TextUtils.isEmpty(modelo) &&
                TextUtils.isEmpty(patente) &&
                TextUtils.isEmpty(motor) &&
                TextUtils.isEmpty(color) &&
                TextUtils.isEmpty(puertas))
    }
}