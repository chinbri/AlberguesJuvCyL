package com.chin.alb.juv.ui.settings

import android.os.Bundle
import android.view.View
import com.chin.presentation.settings.SettingsPresenter
import com.chin.presentation.settings.SettingsView
import com.chin.alb.juv.R
import com.chin.alb.juv.di.settings.SettingsModule
import com.chin.alb.juv.ui.BaseActivity
import com.chin.alb.juv.ui.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_settings.*
import javax.inject.Inject

class SettingsDialogFragment: BaseDialogFragment(), SettingsView {

    @Inject
    lateinit var presenter: SettingsPresenter

    companion object {
        val TAG: String = SettingsDialogFragment::class.java.simpleName

        fun getInstance() = SettingsDialogFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize(this)
        setupView()
    }

    override fun dependencyInjection() {
        (activity as BaseActivity).activityComponent
            .settingsComponentBuilder()
            .settingsModule(SettingsModule())
            .build()
            .inject(this)
    }

    override fun getViewLayout(): Int = R.layout.dialog_settings

    override fun setRationValue(ratio: String) {
        etRatioSearchSetting.setText(ratio)
    }

    override fun getSearchRation(): String = etRatioSearchSetting.text.toString()

    override fun finish() {
        dismiss()
    }

    override fun showWarningSearchRatio() {
        tvErrorRationSearchSetting.visibility = View.VISIBLE
    }

    private fun setupView(){
        btnAccept.setOnClickListener {
            presenter.onAcceptClicked()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }
}