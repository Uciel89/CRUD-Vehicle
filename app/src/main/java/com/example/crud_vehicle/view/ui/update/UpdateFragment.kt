package com.example.crud_vehicle.view.ui.update

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Log.INFO
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crud_vehicle.Pref
import com.example.crud_vehicle.R
import com.example.crud_vehicle.model.Vehicle
import com.example.crud_vehicle.repository.VehicleRepository
import com.example.crud_vehicle.view.ui.description.DescriptionFragmentArgs
import com.example.crud_vehicle.viewmodel.VehicleViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class UpdateFragment : Fragment() {

    private lateinit var mVehicleViewModel: VehicleViewModel
    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // Instanciamos el viewModel de Vehiculos
        mVehicleViewModel = ViewModelProvider(this)[VehicleViewModel::class.java]

        // Recuremas el id almacenado en el SharedPreference
        id = Pref(requireContext()).getString()

        cargarDatos()

        view.findViewById<MaterialButton>(R.id.buttonUpdate).setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun cargarDatos(){

        // Obtenemos un objeto vehicle mediante el id
        val vehicle = mVehicleViewModel.getVehicleById(id.toInt())

        // Tomamos los valores que contiene el objeto recuperado y los establecemos a cada EditText
        vehicle.observe(viewLifecycleOwner, Observer { data ->
            view?.findViewById<TextInputEditText>(R.id.txtEditMarcaUpdate)?.setText(data.marca)
            view?.findViewById<TextInputEditText>(R.id.txtEditModeloUpdate)?.setText(data.modelo)
            view?.findViewById<TextInputEditText>(R.id.txtEditPatenteUpdate)?.setText(data.patente)
            view?.findViewById<TextInputEditText>(R.id.txtEditMotorUpdate)?.setText(data.motor)
            view?.findViewById<TextInputEditText>(R.id.txtEditColorUpdate)?.setText(data.color)
            view?.findViewById<TextInputEditText>(R.id.txtEditPuertasUpdate)?.setText(data.puertas)
        })
    }

    private fun updateItem() {
        // Obtenemos lo ingresado por campos de texto
        val marca = view?.findViewById<TextView>(R.id.txtEditMarcaUpdate)?.text.toString()
        val modelo = view?.findViewById<TextView>(R.id.txtEditModeloUpdate)?.text.toString()
        val patente = view?.findViewById<TextView>(R.id.txtEditPatenteUpdate)?.text.toString()
        val motor = view?.findViewById<TextView>(R.id.txtEditMotorUpdate)?.text.toString()
        val color = view?.findViewById<TextView>(R.id.txtEditColorUpdate)?.text.toString()
        val puertas = view?.findViewById<TextView>(R.id.txtEditPuertasUpdate)?.text.toString()

        if(inputCheck(marca, modelo, patente, motor, color, puertas)){
            // Creamos una instancia de la clase Vehiculo
            val updateVehicle = Vehicle(id.toInt(),marca, modelo, patente, motor, color, puertas)

            // Agregamos estos datos dentro de nuestra base de datos
            mVehicleViewModel.updateVehicle(updateVehicle)
            Toast.makeText(requireContext(), "Vehiculo actualizado correctamente", Toast.LENGTH_SHORT).show()

            // Volvemos a al fragment anterior
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Verificar que todos los campos estén completados", Toast.LENGTH_SHORT).show()
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