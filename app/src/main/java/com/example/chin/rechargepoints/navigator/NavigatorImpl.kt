package com.example.chin.rechargepoints.navigator

import android.content.Intent
import android.net.Uri
import com.example.chin.domain.entities.MapDataEntity
import com.example.chin.domain.entities.RechargePointEntity
import com.example.chin.presentation.main.navigator.Navigator
import com.example.chin.rechargepoints.ui.BaseActivity
import com.example.chin.rechargepoints.ui.about.AboutDialogFragment
import com.example.chin.rechargepoints.ui.address.AddressDialogFragment
import com.example.chin.rechargepoints.ui.map.MapActivity
import com.example.chin.rechargepoints.ui.settings.SettingsDialogFragment
import javax.inject.Inject


class NavigatorImpl @Inject constructor(private val activity: BaseActivity): Navigator {

    override fun goToNavigation(pointEntity: RechargePointEntity) {

        val url = "https://www.google.com/maps/dir/?api=1&destination=${pointEntity.latitude},${pointEntity.longitude}"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        activity.startActivity(i)

    }

    override fun openUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        activity.startActivity(i)
    }

    override fun displaySearchByAddressDialog(lastAddress: String, listener: (newAddress: String) -> Unit) {

        val addressDialogFragment = AddressDialogFragment.getInstance(lastAddress, listener)
        addressDialogFragment.show(
            activity.supportFragmentManager, AddressDialogFragment.TAG
        )

    }

    override fun goToMapScreen(mapDataEntity: MapDataEntity) {
        val intent = MapActivity.getLaunchIntent(activity, mapDataEntity)
        activity.startActivity(intent)
    }

    override fun displaySettingsDialog() {

        SettingsDialogFragment
            .getInstance()
            .show(activity.supportFragmentManager, SettingsDialogFragment.TAG)

    }

    override fun displayAboutDialog() {

        AboutDialogFragment
            .getInstance()
            .show(activity.supportFragmentManager, AboutDialogFragment.TAG)

    }

}