package com.chin.presentation.settings

interface SettingsView {

    fun setRationValue(ratio: String)

    fun getSearchRation(): String

    fun finish()

    fun showWarningSearchRatio()

}