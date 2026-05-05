package com.example.studentcontactapp 

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.studentcontactapp.utils.PrefManager
import com.example.studentcontactapp.utils.SettingsManager

class ProfileFragment : Fragment() {

    private lateinit var prefManager: PrefManager
    private lateinit var settingsManager: SettingsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefManager = PrefManager(requireContext())
        settingsManager = SettingsManager(requireContext())

        // Hubungkan variabel dengan ID di XML
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val switchDarkMode = view.findViewById<Switch>(R.id.switchDarkMode)
        val switchNotification = view.findViewById<Switch>(R.id.switchNotification)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        
        // Mengambil username dari PrefManager, jika null akan menampilkan "User"
        tvUsername.text = "Welcome, ${prefManager.getUsername()}"
        
        switchDarkMode.isChecked = settingsManager.isDarkMode()
        switchNotification.isChecked = settingsManager.isNotificationEnabled()

        //  Switch Dark Mode diganti (ON / OFF)
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            settingsManager.setDarkMode(isChecked)

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        switchNotification.setOnCheckedChangeListener { _, isChecked ->

            settingsManager.setNotificationEnabled(isChecked)
        }

        btnLogout.setOnClickListener {

            prefManager.logout()

           
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)


            requireActivity().finish()
        }
    }
}
