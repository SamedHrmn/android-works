package com.samedharman.recycler_view_basic

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class AnimeDetailActivity : AppCompatActivity() {
    lateinit var txt_animeDetail:TextView
    lateinit var img_animateDetail:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        txt_animeDetail = findViewById(R.id.txt_animeDetail)
        img_animateDetail = findViewById(R.id.img_animeDetail)

        val intent = getIntent()
        val animeName = intent.getStringExtra("animeName")
        val animeImage = intent.getIntExtra("animeImage",0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources,animeImage)
        txt_animeDetail.text = animeName
        img_animateDetail.setImageBitmap(bitmap)
    }
}