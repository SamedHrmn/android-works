package com.example.besinler_kitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.besinler_kitabi.model.FoodModel
import com.example.besinler_kitabi.service.FoodDatabase
import com.example.besinler_kitabi.service.FoodService
import com.example.besinler_kitabi.util.AppSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) : BaseViewModel(application) {

    private val foodService: FoodService = FoodService()
    private val disposable: CompositeDisposable = CompositeDisposable()

    val foodListLiveData = MutableLiveData<ArrayList<FoodModel>>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val errorTextLiveData = MutableLiveData<Boolean>()

    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L
    private val sharedPreferences = AppSharedPreferences(getApplication())

    fun refreshData() {

        val lastUpdate = sharedPreferences.getTime()
        if (lastUpdate != null && lastUpdate != 0L && System.nanoTime() - lastUpdate < updateTime) {
            getDataFromSqlite()
        } else {
            getDataFromService()
        }
    }

    private fun getDataFromSqlite() {
        progressBarLiveData.value = true

        launch {

            val foodList = FoodDatabase(getApplication()).foodDao().getAllFood()
            foodListLiveData.value = foodList as ArrayList<FoodModel>
            errorTextLiveData.value = false
            progressBarLiveData.value = false
            Toast.makeText(getApplication(), "Food fetched from SQLite", Toast.LENGTH_LONG).show()

        }

    }

    fun getDataOnlyService() {
        getDataFromService()
    }

    private fun getDataFromService() {
        progressBarLiveData.value = true
        errorTextLiveData.value = false


        disposable.add(foodService.getFood().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<ArrayList<FoodModel>>() {
                override fun onSuccess(t: ArrayList<FoodModel>) {
                    saveToSqlite(t)
                    Toast.makeText(getApplication(), "Food fetched from Service", Toast.LENGTH_LONG).show()

                    foodListLiveData.value = t
                    errorTextLiveData.value = false
                    progressBarLiveData.value = false

                }

                override fun onError(e: Throwable) {
                    errorTextLiveData.value = true
                    progressBarLiveData.value = false
                    e.printStackTrace()
                }

            }))
    }

    private fun saveToSqlite(foodList: ArrayList<FoodModel>) {

        launch {


            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodList.toTypedArray())
            var i = 0
            while (i < foodList.size) {
                foodList[i].uuid = uuidList[i]
                i += 1
            }

            foodListLiveData.value = foodList
            errorTextLiveData.value = false
            progressBarLiveData.value = false

        }

        sharedPreferences.saveTime(System.nanoTime())
    }

}