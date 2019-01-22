package com.chinsoft.alb.juv.di

import android.content.Context
import androidx.room.Room
import com.chinsoft.presentation.main.navigator.Navigator
import com.chinsoft.alb.juv.database.AppDatabase
import com.chinsoft.alb.juv.navigator.NavigatorImpl
import com.chinsoft.alb.juv.ui.BaseActivity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job

@Module
class ActivityModule(private val activity: BaseActivity) {

    private val roomDatabase by lazy {
        Room.databaseBuilder(
            activity,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).build()
    }

    private val shoppingDao by lazy {
        roomDatabase.rechargePointsDao()
    }

    private val addressDao by lazy {
        roomDatabase.addressDao()
    }

    @Provides
    @ActivityScope
    fun providesContext(): Context {
        return activity
    }

    @Provides
    @ActivityScope
    fun providesActivity(): BaseActivity {
        return activity
    }

    @Provides
    @ActivityScope
    fun providesActivityJob(): Job {
        return activity.job
    }

    @Provides
    @ActivityScope
    fun providesNavigator(impl: NavigatorImpl): Navigator = impl

    @Provides
    @ActivityScope
    fun providesShoppingDao() = shoppingDao

    @Provides
    @ActivityScope
    fun providesAddressDao() = addressDao

}