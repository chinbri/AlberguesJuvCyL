package com.chinsoft.domain.util

interface PreferenceUtils {
    fun saveLastAddress(address: String?)
    fun getLastAddress() : String?
    fun getSearchRatio(): Int
    fun saveSearchRatio(searchRatio: Int)
}