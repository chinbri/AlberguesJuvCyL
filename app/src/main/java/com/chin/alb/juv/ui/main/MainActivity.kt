package com.chin.alb.juv.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.chin.domain.entities.ShelterEntity
import com.chin.presentation.main.ListActions
import com.chin.presentation.main.MainPresenter
import com.chin.presentation.main.MainView
import com.chin.alb.juv.R
import com.chin.alb.juv.di.main.MainModule
import com.chin.alb.juv.ui.BaseActivity
import com.chin.alb.juv.ui.main.adapter.ShelterAdapter
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_footer.*
import javax.inject.Inject



class MainActivity : BaseActivity(), MainView {

    companion object {
        const val REQUEST_LOCATION_SETTINGS = 5327
        const val PREFERENCE_FIRST_EXECUTION = "PREFERENCE_FIRST_EXECUTION"
    }

    private val adapter =
        ShelterAdapter { point: ShelterEntity,
                         action: ListActions ->
            run {
                when (action) {
                    ListActions.INFO -> presenter.onInfoClicked(point)
                    ListActions.NAVIGATION -> presenter.onNavigationSelected(point)
                    ListActions.SELECTION -> presenter.onShelterSelected(point)
                }
            }
        }

    private var locationCallback = LocationCallback()
    private var fusedLocationClient: FusedLocationProviderClient? = null

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(customToolbar as Toolbar)

        title = resources.getString(R.string.main_title)

        activityComponent
            .mainComponentBuilder()
            .mainModule(MainModule()).build()
            .inject(this)

        presenter.initialize(this, obtainBooleanPreference(PREFERENCE_FIRST_EXECUTION))
        saveBooleanPreference(PREFERENCE_FIRST_EXECUTION, false)

        setupView()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun setupView() {

        rvRechagePoints.adapter = adapter
        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_alpha)
        rvRechagePoints.layoutAnimation = animation

        btnFindAll.setOnClickListener {

            presenter.onSearchAllClicked()

        }

        btnFindNear.setOnClickListener{
            Dexter
                .withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        startLocationUpdates()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showMessage(resources.getString(R.string.location_permission_needed))
                    }
                }).check()
        }

        btnFindByAddress.setOnClickListener {
            presenter.onSearchByAddressClicked()
        }

        btnCancel.setOnClickListener{
            stopLocationUpdates()

            showLoadingFooter(false)

            showMessage(resources.getString(R.string.cancelled_search))
        }

        fabMap.setOnClickListener {
            presenter.onFabMapClicked()
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return

                presenter.obtainNearShelters(
                    locationResult.lastLocation.latitude,
                    locationResult.lastLocation.longitude
                )
                stopLocationUpdates()
            }
        }

        checkLocationSettingEnabled()
    }

    override fun showNetworkErrorMessage() {
        showMessage(resources.getString(R.string.network_error))
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        showLoadingFooter(true)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient?.requestLocationUpdates(
            createLocationRequest(),
            locationCallback,
            null /* Looper */)
    }

    private fun stopLocationUpdates() {
        fusedLocationClient?.removeLocationUpdates(locationCallback)
    }

    private fun createLocationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    override fun showSearchOkMessage() {
        showMessage(resources.getString(R.string.search_points_ok))
    }

    override fun drawList(items: List<ShelterEntity>) {
        adapter.shelterList = items
        adapter.notifyDataSetChanged()
        if(items.isNotEmpty()){
            fabMap.show()
            rlNoResults.visibility = View.GONE
            runLayoutAnimation(rvRechagePoints)
            rvRechagePoints.scrollToPosition(0)
        }else{
            fabMap.hide()
            rlNoResults.visibility = View.VISIBLE
        }

    }

    private fun runLayoutAnimation(recyclerView: RecyclerView) {
        val context = recyclerView.context
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_alpha)

        recyclerView.layoutAnimation = controller
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    override fun showCurrentAddressSearch(address: String){
        supportActionBar?.subtitle = resources.getString(R.string.current_address, address)
    }

    override fun hideCurrentAddressSearch(){
        supportActionBar?.subtitle = null
    }

    override fun showLoadingFooter(loading: Boolean) {
        llLoading.visibility = if(loading) View.VISIBLE else View.GONE
        llSearch.visibility = if(loading) View.GONE else View.VISIBLE
    }

    private fun checkLocationSettingEnabled() {
        val locationRequest = createLocationRequest()
        if(locationRequest != null){
            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
            val client = LocationServices.getSettingsClient(this)
            val task = client.checkLocationSettings(builder.build())

            task.addOnFailureListener(this) { e ->
                if (e is ResolvableApiException) {

                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        e.startResolutionForResult(
                            this,
                            REQUEST_LOCATION_SETTINGS
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore the error.
                    }

                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_LOCATION_SETTINGS == requestCode && resultCode != Activity.RESULT_OK) {
            showMessage(resources.getString(R.string.location_desabled_warning))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showAddresNotFoundMessage() {
        showMessage(resources.getString(R.string.cant_find_address))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){

            R.id.menu_settings -> presenter.onMenuSettingsSelected()

            R.id.menu_help -> presenter.onMenuHelpSelected()

        }

        return super.onOptionsItemSelected(item)

    }
}
