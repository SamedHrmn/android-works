package com.samedharman.recycler_view_basic

import java.util.*

class AnimeModel(_animeName: String, _animeImage: Int) {

    val animeName:String
    val animeImage:Int

    init {
        animeName = _animeName.capitalize(Locale.ROOT)
        animeImage = _animeImage
    }
}