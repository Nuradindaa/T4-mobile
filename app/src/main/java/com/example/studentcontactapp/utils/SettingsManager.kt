package com.example.studentcontactapp.utils

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {

    // Membuat file SharedPreferences baru bernama "SettingsPrefs" khusus untuk pengaturan
    private val prefs: SharedPreferences = context.getSharedPreferences("SettingsPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    // --- 1. Dark Mode (Boolean) ---
    fun setDarkMode(isDarkMode: Boolean) {
        editor.putBoolean("DARK_MODE", isDarkMode)
        editor.apply()
    }

    fun isDarkMode(): Boolean {
        // false adalah nilai default jika user belum pernah mengatur dark mode (artinya mode terang)
        return prefs.getBoolean("DARK_MODE", false)
    }

    // --- 2. Font Size (Int) ---
    fun setFontSize(size: Int) {
        editor.putInt("FONT_SIZE", size)
        editor.apply()
    }

    fun getFontSize(): Int {
        // 14 adalah nilai default untuk ukuran font
        return prefs.getInt("FONT_SIZE", 14)
    }

    // --- 3. Notification Enabled (Boolean) ---
    fun setNotificationEnabled(isEnabled: Boolean) {
        editor.putBoolean("NOTIF_ENABLED", isEnabled)
        editor.apply()
    }

    fun isNotificationEnabled(): Boolean {
        // true adalah nilai default (asumsi notifikasi menyala secara default)
        return prefs.getBoolean("NOTIF_ENABLED", true)
    }
}