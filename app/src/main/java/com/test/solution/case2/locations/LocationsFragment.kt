package com.test.solution.case2.locations

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.test.solution.R
import com.test.solution.databinding.FragmentCase2Binding
import android.widget.AdapterView.OnItemSelectedListener
import com.test.solution.FragmentListener
import com.test.solution.case2.managers.NetworkManager
import com.test.solution.case2.map.MapActivity
import com.test.solution.case2.repository.apimodels.Location
import com.test.solution.case2.utils.Config

/**
 * Main fragment class for case 2 - contains a spinner, mode of transport for the locations and navigate option.
 * Retrofit is used to get the locations.
 *
 * created by fahad on 07/08/2018
 */
class LocationsFragment : Fragment() {

    private lateinit var viewModel: LocationsViewModel //view model instance
    private lateinit var binding: FragmentCase2Binding // binding reference
    private lateinit var fragmentListener: FragmentListener
    private lateinit var selectedLocation: Location // object to hold the selected location from the spinner

    //Singleton object
    companion object {
        fun getInstance() = LocationsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Create the viewmodel
        viewModel = ViewModelProviders.of(this).get(LocationsViewModel::class.java);
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        fragmentListener = context as FragmentListener
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Obtain the reference for databinding
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_case_2, container, false);

        //connect the layout with the viewmodel
        binding.viewModel = viewModel

        //set the observers
        setObservers()

        //If the fragment is recreated due to rotation, the locations are not fetched again.
        if (viewModel.locationsAvailable.get() == false){
            if (NetworkManager(context).isConnected) viewModel.getAllLoactions() else fragmentListener.showToastInActivity(getString(R.string.check_internet))
        }
        return binding.root
    }

    private fun setObservers(){

        //observer to display loading
        viewModel.isLoading.observe(this, Observer {
            it?.let { fragmentListener.showProgress(it) }
        })

        //observer to show error
        viewModel.apiError.observe(this, Observer {
            it?.let { fragmentListener.showToastInActivity(it.localizedMessage) }
        })

        //observer to populate the spinner with locations
        viewModel.locations.observe(this, Observer {
            it?.let {
                setSpinnerAdapter(it)
            }
        })

        //observer to handle navigate button click
        viewModel.navigateClicked.observe(this, Observer {
            it?.let { if(it) showMap()}
        })

        //observer to set the selected location
        viewModel.selectedLocation.observe(this, Observer {
            it?.let { selectedLocation = it }
        })
    }

    //set the spinner adapter with the list obtained from web service
    fun setSpinnerAdapter(list: List<String>){
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, list)
        binding.locationSpinner.adapter = adapter
        binding.locationSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                //inform the view model when a location is selected
                viewModel.locationSelected(position)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {

            }

        })
    }

    //Show map activity on clicking navigate button
    fun showMap(){
        viewModel.navigateClicked.value = false
        startActivity(Intent(activity, MapActivity::class.java).putExtra(Config.LOCATION_SELECTED, selectedLocation))
    }
}