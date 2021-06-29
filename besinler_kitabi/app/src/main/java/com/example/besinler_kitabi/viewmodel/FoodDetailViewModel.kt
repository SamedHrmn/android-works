package com.example.besinler_kitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.besinler_kitabi.model.FoodModel
import com.example.besinler_kitabi.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application) : BaseViewModel(application) {

    val foodDetailLiveData = MutableLiveData<FoodModel>()


    fun readDetailFromSqlite(uuid: Long) {
        launch {

            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)
            foodDetailLiveData.value = food
        }
    }
}