package com.chinsoft.alb.juv.ui.map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import com.chinsoft.domain.entities.MapDataEntity
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.presentation.map.MapPresenter
import com.chinsoft.presentation.map.MapScreenView
import com.chinsoft.alb.juv.di.map.MapModule
import com.chinsoft.alb.juv.ui.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.layout_map_footer.*
import javax.inject.Inject
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import android.content.Context
import android.graphics.Canvas
import com.chinsoft.alb.juv.R


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
        setContentView(R.layout.activity_map)

        setSupportActionBar(customToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityComponent
            .mapComponentBuilder()
            .mapModule(MapModule()).build()
            .inject(this)

        title = resources.getString(R.string.main_title)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        presenter.initialize(
            this,
            Gson().fromJson<MapDataEntity>(
                intent.getStringExtra(EXTRA_POINT_LIST),
                MapDataEntity::class.java
            ).shelterList
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        presenter.onMapReady()
    }

    override fun setupMarkers(rechargePointsList: List<ShelterEntity>){

        rechargePointsList.forEach {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(it.latitude, it.longitude))
                    .icon(bitmapDescriptorFromVector(this, R.drawable.ic_house))
            ).tag = it.id
        }

        mMap.setOnMarkerClickListener(this)
        moveCamera(rechargePointsList)

    }

    private fun moveCamera(rechargePointsList: List<ShelterEntity>){
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

            prevMarker?.setIcon(bitmapDescriptorFromVector(this, R.drawable.ic_house))

            marker.setIcon(bitmapDescriptorFromVector(this, R.drawable.ic_house_on))
            prevMarker = marker

        }


        return true

    }



    override fun showRechargePointData(shelterEntity: ShelterEntity) {

        showFooter()

        tvName.text = shelterEntity.name
        tvAddress.text = shelterEntity.address
        tvDescription.text = shelterEntity.description
        tvDistance.text = resources.getString(R.string.distance_label_km, shelterEntity.distance)

        wrapperDistance.visibility = when(shelterEntity.distance) {
            0F -> View.GONE
            else -> View.VISIBLE
        }

        if(shelterEntity.url.isNotEmpty()){
            ivInfo.setOnClickListener {
                presenter.onInfoClicked(shelterEntity)
            }
        }else{
            ivInfo.visibility = View.GONE
        }

        ivNavigation.setOnClickListener{
            presenter.onNavigationClicked(shelterEntity)
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

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        if(vectorDrawable != null){

            vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
            val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            vectorDrawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bitmap)

        }
        return null

    }

}
