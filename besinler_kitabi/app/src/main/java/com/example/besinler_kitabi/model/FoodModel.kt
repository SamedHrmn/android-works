package com.example.besinler_kitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "food")
data class FoodModel(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val foodName: String?,

    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val foodCalorie: String?,

    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val foodCarbonhydrate: String?,

    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val foodProtein: String?,

    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val foodOil: String?,

    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val foodImage: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0
}
