package com.example.crud_vehicle.ui.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_vehicle.R
import com.example.crud_vehicle.data.room.entities.Vehicle
import com.example.crud_vehicle.ui.view.fragment.list.ListFragmentDirections

class ListItemAdapter: RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    private var vehicleList = emptyList<Vehicle>()

    class ListItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtMarca  = itemView.findViewById<TextView>(R.id.txtMarca)!!
        val txtModel = itemView.findViewById<TextView>(R.id.txtModel)!!
        val txtPatente = itemView.findViewById<TextView>(R.id.txtPatente)!!
        val listFragmentLayout = itemView.findViewById<ConstraintLayout>(R.id.itemListLayout)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.vehicle_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val currentVehicle = vehicleList[position]
        holder.txtMarca.text = currentVehicle.marca
        holder.txtModel.text = currentVehicle.modelo
        holder.txtPatente.text = currentVehicle.patente
        holder.listFragmentLayout.setOnClickListener {
            // We pass the object you specified from the side in recyclerview and send it to the fragment of description
            // Pasamos el objeto que precionamos del lado del recyclerview y se lo pasamos al fragment de descripsión
            val action = ListFragmentDirections.actionListFragmentToDescriptionFragment(currentVehicle)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    fun setData (vehicle: List<Vehicle>){
        this.vehicleList = vehicle
        notifyDataSetChanged()
    }


}