package com.example.studentcontactapp.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    // Membuat atau membuka file penyimpanan bernama "StudentAppPrefs"
    private val prefs: SharedPreferences = context.getSharedPreferences("StudentAppPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    // 1. Simpan dan baca status login (isLoggedIn, username)
    fun setLogin(isLoggedIn: Boolean, username: String) {
        editor.putBoolean("IS_LOGGED_IN", isLoggedIn)
        editor.putString("USERNAME", username)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("IS_LOGGED_IN", false)
    }

    fun getUsername(): String? {
        return prefs.getString("USERNAME", "User")
    }

    // 2. Simpan dan baca remember me flag
    fun setRememberMe(isRemembered: Boolean) {
        editor.putBoolean("REMEMBER_ME", isRemembered)
        editor.apply()
    }

    fun isRememberMe(): Boolean {
        return prefs.getBoolean("REMEMBER_ME", false)
    }

    // 3. Method logout() untuk clear session
    fun logout() {
        editor.clear()
        editor.apply()
    }
}