package com.example.besinler_kitabi.service

import com.example.besinler_kitabi.model.FoodModel
import io.reactivex.Single
import retrofit2.http.GET

interface IFoodService {

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    // BASE_URL -> https://raw.githubusercontent.com/
    // atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json


    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getData() : Single<ArrayList<FoodModel>>

}