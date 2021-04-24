package com.darkphoenix.sannadhasena.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

private const val prefsName = "sannadha_sena"


object PrefsManager {
    private var mSharedPref: SharedPreferences? = null

    fun init(context: Context) {
        if (mSharedPref == null) mSharedPref =
            context.getSharedPreferences(prefsName, Activity.MODE_PRIVATE)
    }

    fun read(key: String?, defValue: String?): String? {
        return mSharedPref!!.getString(key, defValue)
    }

    fun write(key: String?, value: String?) {
        val prefsEditor = mSharedPref!!.edit()
        with(prefsEditor) {
            putString(key, value)
            apply()
        }
    }

    fun read(key: String?, defValue: Boolean): Boolean {
        return mSharedPref!!.getBoolean(key, defValue)
    }

    fun write(key: String?, value: Boolean) {
        val prefsEditor = mSharedPref!!.edit()
        with(prefsEditor) {
            putBoolean(key, value)
            apply()
        }
    }

    fun read(key: String?, defValue: Int): Int {
        return mSharedPref!!.getInt(key, defValue)
    }

    fun write(key: String?, value: Int?) {
        val prefsEditor = mSharedPref!!.edit()
        with(prefsEditor) {
            putInt(key, value!!)
            apply()
        }
    }

    fun read(key: String?, defValue: Long): Long {
        return mSharedPref!!.getLong(key, defValue)
    }

    fun write(key: String?, value: Long?) {
        val prefsEditor = mSharedPref!!.edit()
        with(prefsEditor) {
            putLong(key, value!!)
            apply()
        }
    }
}