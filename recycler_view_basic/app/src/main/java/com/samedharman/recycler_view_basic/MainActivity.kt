package com.samedharman.recycler_view_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var animeModelList: ArrayList<AnimeModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animeModelList = ArrayList()

        animeModelList.add(AnimeModel("Death Note",R.drawable.death_note))
        animeModelList.add(AnimeModel("Code Geass",R.drawable.code_geass))
        animeModelList.add(AnimeModel("FullMetal Alchemist",R.drawable.fullmetal_alchemist_brotherhood))


        recyclerView = findViewById(R.id.recycler_anime)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = RecyclerAdapter(animeModelList)
        recyclerView.adapter = adapter


    }
}