package com.example.progettosilicato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.prenotatoFragment)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment), appBarConfiguration)
    }

    /*L'override su onSupportNavigateUp() viene fatto per inizializzare e utilizzare il bottone Up
      Ovvero il bottone "indietro" che permette la navigazione nella gerarchia delle pagine*/
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()

    }
}