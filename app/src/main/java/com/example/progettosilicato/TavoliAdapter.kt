package com.example.progettosilicato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*Un RecyclerView.Adapter è una classe che funziona da ponte tra i dati e le views del RecyclerView*/
class TavoliAdapter(private val tavoliList : ArrayList<Tavoli>, private val listener: TavoliCommunicator) : RecyclerView.Adapter<TavoliAdapter.TavoliViewHolder>() {


    /*Permette di creare un ViewHolder del tipo specifico, così che sia in grado di gestire gli oggetti del tipo dato*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TavoliViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tavoli_item, parent, false)
        return TavoliViewHolder(itemView,/*tavoliListener*/)
    }

    /*Permette di mostrare i dati in una certa posizione dell'oggetto RecyclerView*/
    override fun onBindViewHolder(holder: TavoliViewHolder, position: Int) {
        val currentTavolo = tavoliList[position]
        holder.nPersone.text = currentTavolo.nPersone
        holder.orario.text = currentTavolo.orario
        holder.nTavolo.text = currentTavolo.nTavolo


    }

    /*Restituisce il numero di oggetti all'interno della RecyclerView*/
    override fun getItemCount(): Int {
        return tavoliList.size
    }

    /*Generazione della classe dei ViewHolder*/
    inner class TavoliViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val nPersone : TextView = itemView.findViewById(R.id.tvPersone)
        val orario : TextView = itemView.findViewById(R.id.tvOrario)
        val nTavolo : TextView = itemView.findViewById(R.id.tvnTavolo)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = bindingAdapterPosition
            val persone = tavoliList[bindingAdapterPosition].nPersone
            val orario = tavoliList[bindingAdapterPosition].orario
            val numTavolo = tavoliList[bindingAdapterPosition].nTavolo
            if(position != RecyclerView.NO_POSITION){
                listener.passData(position,persone!!,orario!!,numTavolo!!)
            }
        }
    }
}