package com.groentved.andreas.chordrecognition.data.pref

import android.content.Context
import android.content.SharedPreferences
import groentved.andreas.soitisnowsquat.data.pref.PrefHelper

class AppPreferencesHelper(context: Context) : PrefHelper /*TODO livedata //TODO flere filer eller bedre opdeling*/ {

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    }

    override fun getIsFirstTime(): Boolean = pref.getBoolean(PREF_IS_FIRST_TIME, true)
    override fun setIsFirstTime(firstTime: Boolean): Unit = pref.edit().putBoolean(PREF_IS_FIRST_TIME, firstTime).apply()

    companion object {
        @JvmStatic
        private val PREF_IS_FIRST_TIME = "FIRST_TIME"
        @JvmStatic
        private val PREF = "SQUAT"

    }
}