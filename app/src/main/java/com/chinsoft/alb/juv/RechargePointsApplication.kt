package com.chinsoft.alb.juv

import android.app.Application
import com.chinsoft.alb.juv.di.ApplicationComponent
import com.chinsoft.alb.juv.di.ApplicationModule
import com.chinsoft.alb.juv.di.DaggerApplicationComponent

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