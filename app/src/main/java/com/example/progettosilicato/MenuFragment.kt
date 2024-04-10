package com.example.progettosilicato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.lang.NullPointerException


class MenuFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var menuArrayList: ArrayList<Menu>
    private lateinit var dbReference: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.listaMenu)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)


        menuArrayList = arrayListOf<Menu>()
        getData()
    }

    private fun getData(){
        dbReference = FirebaseDatabase.getInstance("https://database-progettosilicato-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Cibo")
        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(ciboSnapshot in snapshot.children){
                        val cibo = ciboSnapshot.getValue(Menu::class.java)
                        try {
                            menuArrayList.add(cibo!!) //I due !! assicurano che tavolo non sia null, e in caso lo sia lancerà un eccezione
                        }catch (error : NullPointerException){
                            error.printStackTrace()
                            return
                        }
                    }
                    val adapter = MenuAdapter(menuArrayList)
                    recyclerView.adapter = adapter
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                throw p0.toException()
            }

        })
    }
}