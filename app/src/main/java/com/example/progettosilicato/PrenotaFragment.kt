package com.example.progettosilicato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.lang.NullPointerException


class PrenotaFragment : Fragment(), TavoliCommunicator {


    companion object{
        var globalVar = 1
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var tavoliArrayList: ArrayList<Tavoli>
    private lateinit var dbReference: DatabaseReference




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_prenota, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val layoutManager = LinearLayoutManager(context)
            recyclerView = view.findViewById(R.id.listaTavoli)
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(true)


            tavoliArrayList = arrayListOf<Tavoli>()
            getData()

    }

    /*Funzione che si collega al Database esterno e ne estrae i dati all'interno della sezione "Tavoli".
    * Poi per ogni elemento all'interno di tale sezione lo aggiunge ad una ArrayList per poi cederli
    * all'adapter e mostrarli all'interno della recyclerview*/
    private fun getData(){
        dbReference = FirebaseDatabase.getInstance("https://database-progettosilicato-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Tavoli")
        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (tavoloSnapshot in snapshot.children) {
                        val tavolo = tavoloSnapshot.getValue(Tavoli::class.java)
                        try {
                            tavoliArrayList.add(tavolo!!) //I due !! assicurano che tavolo non sia null, e in caso lo sia lancerà un eccezione NullPointerException
                        }catch (error : NullPointerException){
                            error.printStackTrace()
                            return
                        }
                    }
                    var adapter = TavoliAdapter(tavoliArrayList, this@PrenotaFragment)
                    recyclerView.adapter = adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                throw p0.toException()
            }

        })
    }

    override fun passData(position: Int, persone: String, orario: String, numTavolo: String) {
        val bundle = Bundle()
        bundle.putInt("input_pos", position)
        bundle.putString("input_persone", persone)
        bundle.putString("input_orario", orario)
        bundle.putString("input_nTavolo", numTavolo)

        requireView().findNavController().navigate(R.id.action_prenotaFragment_to_confermaFragment, bundle)

    }


}