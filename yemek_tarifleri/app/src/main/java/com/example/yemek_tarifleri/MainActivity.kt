package com.example.yemek_tarifleri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.navArgs

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.yemek_ekle,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val action = YemekListeFragmentDirections.actionYemekListeFragmentToYemekDetay(navigateInfo = "fromMenu")

        when(item.itemId){
            R.id.yemek_ekle_item -> Navigation.findNavController(this,R.id.fragmentContainerView).navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }


}