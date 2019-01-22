package com.chinsoft.alb.juv.utils

import android.content.SharedPreferences
import com.chinsoft.domain.util.PreferenceUtils
import javax.inject.Inject

class PreferenceUtilsImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): PreferenceUtils {

    companion object {
        const val PREFERENCE_ADDRESS = "PREF_ADDRESS"
        const val PREFERENCE_RATIO = "PREF_RATIO"
    }

    override fun saveLastAddress(address: String?){

        sharedPreferences.edit().putString(PREFERENCE_ADDRESS, address).apply()

    }

    override fun getLastAddress() = sharedPreferences.getString(PREFERENCE_ADDRESS, null)

    override fun getSearchRatio() = sharedPreferences.getInt(PREFERENCE_RATIO, 10)

    override fun saveSearchRatio(searchRatio: Int) {

        sharedPreferences.edit().putInt(PREFERENCE_RATIO, searchRatio).apply()

    }

}