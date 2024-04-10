package com.example.progettosilicato

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prenotaFragmentBtn.setOnClickListener{
            if(PrenotaFragment.Companion.globalVar==2){
                val orario = arguments?.getString("inputorario")
                val nTavolo = arguments?.getString("inputnTavolo")
                Toast.makeText(requireActivity(),"Hai gia prenotato il tavolo ${nTavolo} per le ore ${orario}", Toast.LENGTH_SHORT).show()
            }
            else
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_prenotaFragment)
        }

        menuFragmentBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_menuFragment)
        }

        mapsFragmentBtn.setOnClickListener{
            val gmapsIntentUri = Uri.parse("geo:42.53447793621706, 12.562135274316299")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmapsIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}