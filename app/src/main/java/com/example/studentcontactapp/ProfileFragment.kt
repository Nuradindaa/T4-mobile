package com.example.studentcontactapp // Sesuaikan nama package

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
        // Mengubah XML layout menjadi View
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Manager. Karena ini Fragment, kita pakai requireContext() pengganti 'this'
        prefManager = PrefManager(requireContext())
        settingsManager = SettingsManager(requireContext())

        // Hubungkan variabel dengan ID di XML
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val switchDarkMode = view.findViewById<Switch>(R.id.switchDarkMode)
        val switchNotification = view.findViewById<Switch>(R.id.switchNotification)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // 1. Tampilkan nama user yang login
        // Mengambil username dari PrefManager, jika null akan menampilkan "User"
        tvUsername.text = "Welcome, ${prefManager.getUsername()}"

        // 2. Set status awal Switch berdasarkan data yang tersimpan di SettingsManager
        switchDarkMode.isChecked = settingsManager.isDarkMode()
        switchNotification.isChecked = settingsManager.isNotificationEnabled()

        // 3. Aksi ketika Switch Dark Mode diganti (ON / OFF)
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Simpan pilihan ke SettingsManager
            settingsManager.setDarkMode(isChecked)

            // (Opsional/Tambahan) Kode di bawah ini untuk benar-benar mengubah tema layar HP saat itu juga
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // 4. Aksi ketika Switch Notification diganti
        switchNotification.setOnCheckedChangeListener { _, isChecked ->
            // Simpan pilihan ke SettingsManager
            settingsManager.setNotificationEnabled(isChecked)
        }

        // 5. Aksi Tombol Logout
        btnLogout.setOnClickListener {
            // Hapus session (isLoggedIn jadi false, username dll terhapus)
            prefManager.logout()

            // Pindah kembali ke LoginActivity
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)

            // Tutup Activity saat ini (MainActivity) agar tidak bisa di-back setelah logout
            requireActivity().finish()
        }
    }
}