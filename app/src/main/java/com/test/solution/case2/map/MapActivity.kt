package com.test.solution.case2.map

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.solution.BaseActivity
import com.test.solution.R
import com.test.solution.case2.utils.Config

/**
 * Map activity to show the location selected.
 *
 * created by fahad on 07/08/2018
 */
class MapActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        //Basic setup for displaying map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //get the location from the intent received.
        val location = intent.getParcelableExtra<com.test.solution.case2.repository.apimodels.Location>(Config.LOCATION_SELECTED)

        //create LatLng object from the position.
        val latLng = LatLng(location.latLng.latitude, location.latLng.longitude)

        //Add the marker in the map.
        mMap.addMarker(MarkerOptions().position(latLng).title(location.name))

        //Zoom the camera to the specified location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.0f))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
