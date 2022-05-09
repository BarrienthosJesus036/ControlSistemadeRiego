package com.example.controlsistemaderiego

import android.Manifest
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telephony.SmsManager
import android.widget.Button
import android.widget.Toast
import java.lang.Exception

class Dashboard : AppCompatActivity() {
    val DESTINO = "7445764331"

    val datos_msj = mutableListOf<String>()
    val datos_hora = mutableListOf<String>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(findViewById(R.id.toolbar))
        PERMISSION_SEND_MSJ()
        initComponents()
    }

    private fun PERMISSION_SEND_MSJ(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1000)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initComponents() {
        findViewById<Button>(R.id.btn_encender).setOnClickListener { SEND_MSJ(getString(R.string.dash_on)) }
        findViewById<Button>(R.id.btn_apagar).setOnClickListener { SEND_MSJ(getString(R.string.dash_off)) }
        findViewById<Button>(R.id.btn_estado).setOnClickListener { SEND_MSJ(getString(R.string.dash_state)) }
        findViewById<Button>(R.id.btn_reporte).setOnClickListener { SEND_MSJ(getString(R.string.dash_report)) }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun SEND_MSJ(msj: String){
        datos_msj.add(msj)
        val calendario = Calendar.getInstance()
        datos_hora.add("${calendario.time}")
        RefreshView()
        try {
            val sms: SmsManager = SmsManager.getDefault()
            sms.sendTextMessage(DESTINO, null, msj, null, null)
            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_LONG)
        }catch (ex: Exception){
            Toast.makeText(this, "Error al intentar enviar mensaje ${ex.localizedMessage}", Toast.LENGTH_LONG)
            ex.printStackTrace()
        }
    }

    private fun RefreshView(){
        val recycle = findViewById<RecyclerView>(R.id.recycleview)
        recycle.setHasFixedSize(true)
        recycle.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterModel(datos_msj as ArrayList<String>, datos_hora as ArrayList<String>)
        recycle.adapter = adapter
    }
}