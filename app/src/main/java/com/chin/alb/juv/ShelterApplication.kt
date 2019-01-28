package com.chin.alb.juv

import android.app.Application
import com.chin.alb.juv.di.ApplicationComponent
import com.chin.alb.juv.di.ApplicationModule
import com.chin.alb.juv.di.DaggerApplicationComponent

class ShelterApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)

    }

}