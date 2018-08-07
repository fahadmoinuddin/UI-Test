package com.test.solution.case2.locations

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.test.solution.case2.repository.Repository
import com.test.solution.case2.repository.apimodels.Location
import com.test.solution.case2.repository.apimodels.Transport

/**
 * View model class for Locations fragment.
 *
 * created by fahad on 07/08/2018
 */
class LocationsViewModel : ViewModel() {

    //repository instance
    val repository = Repository.getInstance()

    //List of locations
    var locationList : List<Location>? = null

    //Live data objects which are listened in the fragment to manipulate the view.
    val isLoading = MutableLiveData<Boolean>()
    val apiError = MutableLiveData<Throwable>()
    val locations = MutableLiveData<List<String>>()
    val navigateClicked = MutableLiveData<Boolean>()
    val selectedLocation = MutableLiveData<Location>()

    //Observable fields for data binding with layout
    val locationsAvailable = ObservableField<Boolean>(false)
    val timeFromCentral = ObservableField<Transport>()

    //Function to get all the locations from the repository
    fun getAllLoactions() {
        isLoading.value = true
        //call repository with successhandler and failurehandler
        repository.getAllLocations(
                {
                    isLoading.value = false
                    locationList = it
                    locationList?.let {
                        val tempLocations = ArrayList<String>()
                        for(location in locationList!!){
                            tempLocations.add(location.name)
                        }
                        locations.value = tempLocations
                    }

                    //set locationsAvailable as true for databinding
                    locationsAvailable.set(true)
                },
                {
                    isLoading.value = false
                    apiError.value = it
                }
        )
    }

    //Called when an item is selected in the spinner
    fun locationSelected(position: Int){
        selectedLocation.value = locationList?.get(position)
        timeFromCentral.set(selectedLocation.value?.timeFromCentral)
    }

    //Called when navigate button is clicked
    fun navigate(){
        navigateClicked.value = true
    }
}