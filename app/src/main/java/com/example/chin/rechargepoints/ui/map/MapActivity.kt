package com.example.chin.rechargepoints.ui.map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import com.example.chin.domain.entities.MapDataEntity
import com.example.chin.domain.entities.RechargePointEntity
import com.example.chin.presentation.map.MapPresenter
import com.example.chin.presentation.map.MapScreenView
import com.example.chin.rechargepoints.R
import com.example.chin.rechargepoints.di.map.MapModule
import com.example.chin.rechargepoints.ui.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.layout_map_footer.*
import javax.inject.Inject


class MapActivity : BaseActivity(), MapScreenView, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    @Inject
    lateinit var presenter: MapPresenter

    private lateinit var mMap: GoogleMap

    private var prevMarker: Marker? = null

    companion object {
        const val EXTRA_POINT_LIST = "EXTRA_POINT_LIST"
        private val gson = Gson()

        fun getLaunchIntent(
            activity: Activity,
            mapDataEntity: MapDataEntity
        ): Intent {
            val intent = Intent(activity, MapActivity::class.java)
            intent.putExtra(EXTRA_POINT_LIST, gson.toJson(mapDataEntity))
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.chin.rechargepoints.R.layout.activity_map)

        setSupportActionBar(customToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityComponent
            .mapComponentBuilder()
            .mapModule(MapModule()).build()
            .inject(this)

        title = resources.getString(R.string.main_title)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(com.example.chin.rechargepoints.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        presenter.initialize(
            this,
            Gson().fromJson<MapDataEntity>(
                intent.getStringExtra(EXTRA_POINT_LIST),
                MapDataEntity::class.java
            ).rechargePointList
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        presenter.onMapReady()
    }

    override fun setupMarkers(rechargePointsList: List<RechargePointEntity>){

        rechargePointsList.forEach {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(it.latitude, it.longitude))
                    .icon(BitmapDescriptorFactory.fromResource(com.example.chin.rechargepoints.R.drawable.e_station))
            ).tag = it.id
        }

        mMap.setOnMarkerClickListener(this)
        moveCamera(rechargePointsList)

    }

    private fun moveCamera(rechargePointsList: List<RechargePointEntity>){
        val builder = LatLngBounds.Builder()
        for (point in rechargePointsList) {
            builder.include(LatLng(point.latitude, point.longitude))
        }
        val bounds = builder.build()

        val padding = 60 // offset from edges of the map in pixels
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)

        mMap.animateCamera(cu)
    }

    override fun onMarkerClick(marker: Marker?): Boolean {

        //leave Marker default color if re-click current Marker
        if (marker != null
            && (prevMarker == null || marker != prevMarker)) {

            presenter.onMarkerClicked(marker.tag as String)

            prevMarker?.setIcon(BitmapDescriptorFactory.fromResource(com.example.chin.rechargepoints.R.drawable.e_station))

            marker.setIcon(BitmapDescriptorFactory.fromResource(com.example.chin.rechargepoints.R.drawable.e_station_on))
            prevMarker = marker

        }


        return true

    }



    override fun showRechargePointData(rechargePointEntity: RechargePointEntity) {

        showFooter()

        tvName.text = rechargePointEntity.name
        tvAddress.text = rechargePointEntity.address
        tvDescription.text = rechargePointEntity.description
        tvDistance.text = resources.getString(com.example.chin.rechargepoints.R.string.distance_label_km, rechargePointEntity.distance)

        if(rechargePointEntity.url.isNotEmpty()){
            ivInfo.setOnClickListener {
                presenter.onInfoClicked(rechargePointEntity)
            }
        }else{
            ivInfo.visibility = View.GONE
        }

        ivNavigation.setOnClickListener{
            presenter.onNavigationClicked(rechargePointEntity)
        }

    }

    private fun showFooter() {

        if(lyFooterMap.visibility != View.VISIBLE){
            val bottomUp = AnimationUtils.loadAnimation(
                this,
                R.anim.bottom_in
            )
            lyFooterMap.startAnimation(bottomUp)
            lyFooterMap.visibility = View.VISIBLE
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
