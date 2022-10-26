package com.example.crud_vehicle.ui.view.fragment.description

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crud_vehicle.core.Pref
import com.example.crud_vehicle.R
import com.example.crud_vehicle.ui.viewmodel.VehicleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DescriptionFragment : Fragment() {

    private val args by navArgs<DescriptionFragmentArgs>()
    private lateinit var mVehicleViewModel: VehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_description, container, false)

        mVehicleViewModel = ViewModelProvider(this)[VehicleViewModel::class.java]

        // Mostramos los valores del objeto obtenido en el anterior fragment
        view.findViewById<TextView>(R.id.textDescriptionMarca).text = args.currentVehicle.marca
        view.findViewById<TextView>(R.id.textDescriptionModelo).text = args.currentVehicle.modelo
        view.findViewById<TextView>(R.id.textDescriptionPatente).text = args.currentVehicle.patente
        view.findViewById<TextView>(R.id.textDescriptionMotor).text = args.currentVehicle.motor
        view.findViewById<TextView>(R.id.textDescriptionColor).text = args.currentVehicle.color
        view.findViewById<TextView>(R.id.textDescriptionPuertas).text = args.currentVehicle.puertas

        // :: Botón actualizar ::
        view.findViewById<FloatingActionButton>(R.id.fabEdit).setOnClickListener {

            // Guardamos el id dentro del SharedPreference
            Pref(requireContext()).saveString(args.currentVehicle.id.toString())

            // Navegamos hacia el fragment de edición
            findNavController().navigate(R.id.action_descriptionFragment_to_updateFragment)
        }

        // :: Botón eliminar ::
        view?.findViewById<FloatingActionButton>(R.id.fabDelete)?.setOnClickListener {
            deleteVehicle()
        }

        return view
    }

    private fun deleteVehicle() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si") {_ , _ ->
            mVehicleViewModel.deleteVehicle(args.currentVehicle)
            Toast.makeText(requireContext(),
                "Se elimino correctamente el vehiculo ${args.currentVehicle.marca} marca ${args.currentVehicle.modelo}",
                Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_descriptionFragment_to_listFragment3)
        }
        builder.setNegativeButton("No") {_,_ -> }
        builder.setTitle("Eliminar ${args.currentVehicle.marca}")
        builder.setMessage("Está seguro de eliminar el vehiculo ${args.currentVehicle.marca} marca ${args.currentVehicle.modelo}")
        builder.create().show()
    }
}