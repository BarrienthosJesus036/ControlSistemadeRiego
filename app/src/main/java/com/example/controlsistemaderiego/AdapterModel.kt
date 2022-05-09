package com.example.controlsistemaderiego

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AdapterModel(array_msj: ArrayList<String>, array_hora: ArrayList<String>) : RecyclerView.Adapter<AdapterModel.AdapterViewModel>() {
    val array_msj = array_msj
    val array_hora = array_hora

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdapterModel.AdapterViewModel {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_list, null, false)
        return AdapterViewModel(view)
    }

    override fun onBindViewHolder(p0: AdapterModel.AdapterViewModel, p1: Int) {
        p0.txt_msj.text = array_msj[p1]
        p0.txt_hora.text = array_hora[p1]
    }

    override fun getItemCount(): Int {
        return array_msj.size
    }
    class AdapterViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_msj = itemView.findViewById<TextView>(R.id.txt_msj)
        val txt_hora = itemView.findViewById<TextView>(R.id.txt_hora)
    }
}