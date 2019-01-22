package com.chinsoft.alb.juv.ui.about

import android.os.Bundle
import android.view.View
import com.chinsoft.alb.juv.BuildConfig
import com.chinsoft.alb.juv.R
import com.chinsoft.alb.juv.ui.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_about.*

class AboutDialogFragment: BaseDialogFragment() {

    companion object {
        val TAG: String = AboutDialogFragment::class.java.simpleName

        fun getInstance() = AboutDialogFragment()
    }

    override fun dependencyInjection() {
    }

    override fun getViewLayout(): Int = R.layout.dialog_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){

        tvVersion.text = resources.getString(R.string.version_label, BuildConfig.VERSION_NAME)

        btnAccept.setOnClickListener {
            dismiss()
        }

    }
}