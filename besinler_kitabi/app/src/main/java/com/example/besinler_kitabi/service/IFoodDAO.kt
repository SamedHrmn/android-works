package com.example.besinler_kitabi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.besinler_kitabi.model.FoodModel

@Dao
interface IFoodDAO {

    @Insert
    suspend fun insertAll(vararg food : FoodModel) : List<Long>

    @Query("SELECT * FROM food")
    suspend fun getAllFood() : List<FoodModel>

    @Query("SELECT * FROM food WHERE uuid = :foodId")
    suspend fun getFood(foodId : Long) : FoodModel

    @Query("DELETE FROM food")
    suspend fun deleteAllFood()

}