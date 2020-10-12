package com.example.submission5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.submission5.R
import com.example.submission5.model.getdata.DataItem

class Adapter_Main(val data : List<DataItem?>?, val itemClick : onClickListener) : RecyclerView.Adapter<Adapter_Main.ViewHolder>() {

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val nama = view.findViewById<TextView>(R.id.tv_nama)
        val nohp = view.findViewById<TextView>(R.id.tv_nohp)
        val alamat = view.findViewById<TextView>(R.id.tv_alamat)
        val btn_hapus = view.findViewById<ImageView>(R.id.iv_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user_main, parent, false)
    return ViewHolder((view))
    }

    override fun getItemCount(): Int = data!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama?.text = "Nama : " + item?.nama
        holder.nohp?.text = "NoHP : " + item?.nohp
        holder.alamat?.text = "Alamat : " + item?.alamat

        holder.btn_hapus.setOnClickListener{
            itemClick.hapus(item)
        }

        holder.view.setOnClickListener{
            itemClick.detail(item)
        }
    }
    interface onClickListener{
        fun detail(item: DataItem?)
        fun hapus(item: DataItem?)
    }

}