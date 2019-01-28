package com.chin.alb.juv.navigator

import android.content.Intent
import android.net.Uri
import com.chin.domain.entities.MapDataEntity
import com.chin.domain.entities.ShelterEntity
import com.chin.presentation.main.navigator.Navigator
import com.chin.alb.juv.ui.BaseActivity
import com.chin.alb.juv.ui.about.AboutDialogFragment
import com.chin.alb.juv.ui.address.AddressDialogFragment
import com.chin.alb.juv.ui.map.MapActivity
import com.chin.alb.juv.ui.settings.SettingsDialogFragment
import com.chin.alb.juv.ui.welcome.WelcomeWizardActivity
import javax.inject.Inject


class NavigatorImpl @Inject constructor(private val activity: BaseActivity): Navigator {

    override fun goToNavigation(pointEntity: ShelterEntity) {

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

    override fun goToWelcomeWizard() {
        activity.startActivity(WelcomeWizardActivity.getLaunchIntent(activity))
    }

}