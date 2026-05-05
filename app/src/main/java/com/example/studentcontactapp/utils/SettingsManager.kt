package com.example.studentcontactapp.utils

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("SettingsPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun setDarkMode(isDarkMode: Boolean) {
        editor.putBoolean("DARK_MODE", isDarkMode)
        editor.apply()
    }

    fun isDarkMode(): Boolean {
        return prefs.getBoolean("DARK_MODE", false)
    }
    fun setFontSize(size: Int) {
        editor.putInt("FONT_SIZE", size)
        editor.apply()
    }

    fun getFontSize(): Int {
        return prefs.getInt("FONT_SIZE", 14)
    }
    fun setNotificationEnabled(isEnabled: Boolean) {
        editor.putBoolean("NOTIF_ENABLED", isEnabled)
        editor.apply()
    }

    fun isNotificationEnabled(): Boolean {
        return prefs.getBoolean("NOTIF_ENABLED", true)
    }
}
