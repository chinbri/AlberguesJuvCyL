package com.example.chin.presentation.settings

import com.example.chin.domain.util.PreferenceUtils
import java.lang.Integer.parseInt
import javax.inject.Inject

class SettingsPresenterImpl @Inject constructor(
    private val preferenceUtils: PreferenceUtils
): SettingsPresenter {

    lateinit var view: SettingsView

    override fun initialize(view: SettingsView) {
        this.view = view

        view.setRationValue(preferenceUtils.getSearchRatio().toString())
    }

    override fun onAcceptClicked() {

        try {

            val value = parseInt(view.getSearchRation())

            if(value in 1..200){

                preferenceUtils.saveSearchRatio(value)
                view.finish()

            }else{

                view.showWarningSearchRatio()

            }

        } catch (e: NumberFormatException) {
            view.showWarningSearchRatio()
        }

    }

}