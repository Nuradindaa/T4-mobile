package com.example.studentcontactapp 

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentcontactapp.utils.PrefManager

class LoginActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    
        prefManager = PrefManager(this)

        // CEK AUTO-LOGIN
        if (prefManager.isRememberMe() && prefManager.isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup LoginActivity agar tidak bisa di-back
            return // Menghentikan eksekusi kode di bawahnya
        }

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

            // VALIDASI
            if (username == "admin" && password == "123456") {

                // Simpan data login dan status remember me ke SharedPreferences
                prefManager.setLogin(true, username)
                prefManager.setRememberMe(isRemembered)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()

            } else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
