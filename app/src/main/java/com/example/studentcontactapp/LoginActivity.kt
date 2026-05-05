package com.example.studentcontactapp // Sesuaikan dengan nama package-mu!

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentcontactapp.utils.PrefManager // Pastikan import ini sesuai dengan lokasi PrefManager-mu

class LoginActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi PrefManager
        prefManager = PrefManager(this)

        // CEK AUTO-LOGIN: Jika Remember Me aktif DAN sudah login, langsung pindah ke MainActivity
        if (prefManager.isRememberMe() && prefManager.isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup LoginActivity agar tidak bisa di-back
            return // Menghentikan eksekusi kode di bawahnya
        }

        // Menghubungkan ID dari layout XML ke variabel Kotlin
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val cbRememberMe = findViewById<CheckBox>(R.id.cbRememberMe)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Aksi ketika tombol Login ditekan
        btnLogin.setOnClickListener {
            // Mengambil teks yang diketik user
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val isRemembered = cbRememberMe.isChecked

            // VALIDASI: Sesuai instruksi, username = admin, password = 123456
            if (username == "admin" && password == "123456") {

                // Simpan data login dan status remember me ke SharedPreferences
                prefManager.setLogin(true, username)
                prefManager.setRememberMe(isRemembered)

                // Pindah halaman ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // Tutup LoginActivity agar user tidak kembali ke halaman login saat menekan tombol "Back" di HP
                finish()

            } else {
                // Tampilkan pesan error jika salah
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}