package org.bedu.roomvehicles.vehiclelist

import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.bedu.roomvehicles.R
import org.bedu.roomvehicles.VehiclesApplication
import org.bedu.roomvehicles.data.local.Vehicle
import org.bedu.roomvehicles.databinding.FragmentVehicleListBinding
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * A fragment representing a list of Items.
 */
class VehicleListFragment : Fragment() {


    private lateinit var addButton: FloatingActionButton
    private lateinit var recyclerVehicle: RecyclerView
    private lateinit var vechicleAdapter: VehicleAdapter
    private lateinit var viewModel: VehicleListViewModel
    private lateinit var binding: FragmentVehicleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vehicle_list, container, false)

        viewModel = VehicleListViewModel(
            (requireContext().applicationContext as VehiclesApplication).vehicleRepository
        )

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_vehicle_list,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.apply {
            //recyclerVehicle = binding.list
            buttonAdd.setOnClickListener {
                findNavController().navigate(
                    R.id.action_vehicleListFragment_to_addEditFragment
                )
            }
        }

        setupEditVehicle()
        setUpdateVehicleList()

        //recyclerVehicle = binding.list


        return binding.root
    }

    private fun setupEditVehicle(){

        with(viewModel) {

            eventEditVehicle.observe(viewLifecycleOwner, Observer{
              if (eventEditVehicle.value!=null) {
                  findNavController().navigate(
                      R.id.action_vehicleListFragment_to_addEditFragment,
                      bundleOf("vehicle_id" to eventEditVehicle.value!!))
              }
            })
        }
    }

    private fun setUpdateVehicleList() {

        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        executor.execute{
            val vehicles = viewModel.getVehicleList()
            Handler(Looper.getMainLooper()).post{
                vechicleAdapter = VehicleAdapter(viewModel)
                vechicleAdapter.submitList(vehicles)
                binding.list.apply {
                    adapter = vechicleAdapter
                }
            }
        }
    }
}