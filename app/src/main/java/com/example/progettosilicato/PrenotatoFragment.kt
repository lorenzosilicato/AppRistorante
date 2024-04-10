package com.example.progettosilicato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_prenotato.*


class PrenotatoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prenotato, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nPersone: TextView = requireView().findViewById(R.id.tvPersoneConfermaPrenotato)
        nPersone.text = arguments?.getString("inputpersone")

        val orario: TextView = requireView().findViewById(R.id.tvOrarioConfermaPrenotato)
        orario.text = arguments?.getString("inputorario")
        val orarioB=orario.text.toString()

        val nTavolo: TextView = requireView().findViewById(R.id.tvnTavoloPrenotato)
        nTavolo.text = arguments?.getString("inputnTavolo")
        val nTavoloB=nTavolo.text.toString()


        homeBtn.setOnClickListener{
            passData(orarioB,nTavoloB)
        }
    }

    private fun passData(orario: String, numTavolo: String) {
        val bundleC = Bundle()
        bundleC.putString("inputorario", orario)
        bundleC.putString("inputnTavolo", numTavolo)

        requireView().findNavController().navigate(R.id.action_prenotatoFragment_to_homeFragment, bundleC)

    }

}