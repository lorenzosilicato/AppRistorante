package com.example.progettosilicato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val menuList : ArrayList<Menu>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(itemView, /*menuListener*/)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu : Menu = menuList[position]
        holder.nomeCibo.text = menu.nomeCibo
        holder.prezzoCibo.text = menu.prezzoCibo
        holder.descrizioneCibo.text = menu.descrizioneCibo
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nomeCibo : TextView = itemView.findViewById(R.id.nomeCibo)
        var prezzoCibo : TextView = itemView.findViewById(R.id.prezzoCibo)
        var descrizioneCibo : TextView = itemView.findViewById(R.id.descrizioneCibo)
    }
}