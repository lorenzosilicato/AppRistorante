package com.example.progettosilicato

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_conferma.*


class ConfermaFragment : Fragment() {


    private lateinit var dbReference: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conferma, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nPersone : TextView = requireView().findViewById(R.id.tvPersoneConferma)
        nPersone.text = arguments?.getString("input_persone")
        val personeB=nPersone.text.toString()

        val orario : TextView = requireView().findViewById(R.id.tvOrarioConferma)
        orario.text = arguments?.getString("input_orario")
        val orarioB=orario.text.toString()

        val nTavolo : TextView = requireView().findViewById(R.id.tvnTavolo)
        nTavolo.text = arguments?.getString("input_nTavolo")
        val nTavoloB=nTavolo.text.toString()


        confermaBtn.setOnClickListener{
            val nomeCheck : EditText = requireView().findViewById(R.id.inserisciNome)
            if(TextUtils.isEmpty(nomeCheck.text.toString())){
                Toast.makeText(requireActivity(),"Inserisci il tuo nome per prenotare!",Toast.LENGTH_SHORT).show()
            }
            else{
                PrenotaFragment.Companion.globalVar=2
                dbReference = FirebaseDatabase.getInstance("https://database-progettosilicato-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Prenotazioni")
                val inserisciNome = nomeCheck.text.toString()
                val numeroTavolo=nTavolo.text.toString()
                dbReference.child("Prenotazione${numeroTavolo}").child("nomePrenotazione").setValue(inserisciNome)
                dbReference.child("Prenotazione${numeroTavolo}").child("nTavolo").setValue(numeroTavolo)
                passData(personeB,orarioB,nTavoloB)
            }
        }

        disdiciBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_confermaFragment_to_prenotaFragment)
        }



    }

     private fun passData(persone: String, orario: String, numTavolo: String) {
        val bundleC = Bundle()
        bundleC.putString("inputpersone", persone)
        bundleC.putString("inputorario", orario)
        bundleC.putString("inputnTavolo", numTavolo)

         requireView().findNavController().navigate(R.id.action_confermaFragment_to_prenotatoFragment2, bundleC)

    }
}