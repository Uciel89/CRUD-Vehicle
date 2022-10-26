package com.example.crud_vehicle.ui.view.fragment.list

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_vehicle.R
import com.example.crud_vehicle.ui.viewmodel.ListItemAdapter
import com.example.crud_vehicle.ui.viewmodel.VehicleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var mVehicleModel: VehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Establecemos la acción del botón para retroceder del celular
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.listFragment)
        }
        callback.isEnabled = true

        // Conf recyclerView
        val adapter = ListItemAdapter()
        val recyclerViewList = view.findViewById<RecyclerView>(R.id.recyclerViewList)
        recyclerViewList.adapter = adapter
        recyclerViewList.layoutManager = LinearLayoutManager(requireContext())

        mVehicleModel = ViewModelProvider(this)[VehicleViewModel::class.java]
        mVehicleModel.getAllVehicles().observe(viewLifecycleOwner, Observer { vehicles ->
            adapter.setData(vehicles)
        } )

        val nav = Navigation.createNavigateOnClickListener(R.id.nav_list_to_add)
        view.findViewById<FloatingActionButton>(R.id.addVehicleButton).setOnClickListener {
            // findNavController().navigate(R.id.action_listFragment_to_addVehicleFragment)
            nav.onClick(it)
        }

        return view
    }
}