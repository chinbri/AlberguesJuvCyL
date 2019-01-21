package com.example.chin.rechargepoints

import android.app.Application
import com.example.chin.rechargepoints.di.ApplicationComponent
import com.example.chin.rechargepoints.di.ApplicationModule
import com.example.chin.rechargepoints.di.DaggerApplicationComponent

class RechargePointsApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)

    }

}