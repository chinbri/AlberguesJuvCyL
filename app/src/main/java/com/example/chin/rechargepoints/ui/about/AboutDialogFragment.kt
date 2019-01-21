package com.example.chin.rechargepoints.ui.about

import android.os.Bundle
import android.view.View
import com.example.chin.rechargepoints.BuildConfig
import com.example.chin.rechargepoints.R
import com.example.chin.rechargepoints.ui.BaseDialogFragment
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