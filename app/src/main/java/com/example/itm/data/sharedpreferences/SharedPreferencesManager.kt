package com.example.itm.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class SharedPreferencesManager(context: Context) {

    private val preferences: SharedPreferences =
        SharedPreferencesCreator.createSharedPreferences(context)

    fun setSharedPreferenceString(key: String, value: String) {
        val editor: Editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun removeSharedPreference(key: String) {
        val editor: Editor = preferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun getSharedPreferenceString(key: String): String? {
        return preferences.getString(key, null)
    }
}