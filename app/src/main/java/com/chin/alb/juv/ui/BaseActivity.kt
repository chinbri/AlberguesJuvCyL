package com.chin.alb.juv.ui

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chin.alb.juv.ShelterApplication
import com.chin.alb.juv.di.ActivityComponent
import com.chin.alb.juv.di.ActivityModule
import kotlinx.coroutines.Job
import android.widget.Toast
import android.view.Gravity
import com.chin.alb.juv.R
import com.chin.alb.juv.di.DaggerActivityComponent


abstract class BaseActivity: AppCompatActivity() {

    companion object {
        const val SHARED_PREFERENCES = "ALB_JYC_PREF"
    }

    val job: Job = Job()

    val activityComponent : ActivityComponent by lazy{
        DaggerActivityComponent.builder()
            .applicationComponent((application as ShelterApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun showMessage(message: String){
        val layout = layoutInflater.inflate(R.layout.toast_layout, null)

        val text = layout.findViewById(R.id.text) as TextView
        text.text = message

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }

    fun obtainBooleanPreference(preferenceName: String) =
        getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            .getBoolean(preferenceName, true)


    fun saveBooleanPreference(preferenceName: String, value: Boolean){
        getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(preferenceName, value)
            .apply()
    }
}