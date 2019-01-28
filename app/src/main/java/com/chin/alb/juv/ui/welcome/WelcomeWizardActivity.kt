package com.chin.alb.juv.ui.welcome

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.chin.alb.juv.R
import com.chin.alb.juv.ui.BaseActivity
import com.chin.alb.juv.ui.images.ImageAdapter
import kotlinx.android.synthetic.main.activity_wizard.*

class WelcomeWizardActivity: BaseActivity() {


    companion object {
        fun getLaunchIntent(
            activity: Activity
        ): Intent {
            return Intent(activity, WelcomeWizardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard)
        setupView()
    }

    private fun setupView() {
        welcomePager.adapter = ImageAdapter(
            this,
            getWelcomeImageFiles())

        welcomePager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if(position + 1 == welcomePager.adapter?.count){
                    btnClose.visibility = View.VISIBLE
                }
            }

        })

        btnClose.setOnClickListener { finish() }
    }

    private fun getWelcomeImageFiles() = resources.getStringArray(R.array.array_wizard_images)
        .toList().map {
            "file:///android_asset/$it"
        }

}