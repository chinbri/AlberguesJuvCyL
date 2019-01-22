package com.chinsoft.presentation.settings

interface SettingsView {

    fun setRationValue(ratio: String)

    fun getSearchRation(): String

    fun finish()

    fun showWarningSearchRatio()

}