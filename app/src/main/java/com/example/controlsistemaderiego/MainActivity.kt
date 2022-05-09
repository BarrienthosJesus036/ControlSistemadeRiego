package com.example.controlsistemaderiego

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val USER: String = "admin"
    val PASS: String = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (findViewById<Button>(R.id.btn_access)).setOnClickListener { Validar() }
    }

    private fun Validar(){
        val user = findViewById<EditText>(R.id.txt_usuario).text.trim().toString()
        val pass = findViewById<EditText>(R.id.txt_pass).text.trim().toString()

        if(USER.equals(user) && PASS.equals(pass)) startActivity(Intent(this, Dashboard::class.java))
        else Msj("Alerta", "Error Usuario / Contraseña incorrectos")
    }

    private fun Msj(title: String, msj : String){
        AlertDialog.Builder(this).also {
            it.setTitle(title)
            it.setMessage(msj)
            it.setPositiveButton("Entendido", null)
            it.create()
            it.show()
        }
    }
}