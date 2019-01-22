package com.chinsoft.alb.juv.ui

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chinsoft.alb.juv.RechargePointsApplication
import com.chinsoft.alb.juv.di.ActivityComponent
import com.chinsoft.alb.juv.di.ActivityModule
import kotlinx.coroutines.Job
import android.widget.Toast
import android.view.Gravity
import com.chinsoft.alb.juv.R
import com.chinsoft.alb.juv.di.DaggerActivityComponent


abstract class BaseActivity: AppCompatActivity() {

    val job: Job = Job()

    val activityComponent : ActivityComponent by lazy{
        DaggerActivityComponent.builder()
            .applicationComponent((application as RechargePointsApplication).applicationComponent)
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
}