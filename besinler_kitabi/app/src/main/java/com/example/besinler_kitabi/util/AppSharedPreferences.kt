package com.example.besinler_kitabi.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class AppSharedPreferences {
    companion object {

        private val TIME = "time"
        private var sharedPreferences : SharedPreferences? = null


        @Volatile private var instance : AppSharedPreferences? = null
        private val lock = Any()
        operator fun invoke(context: Context) : AppSharedPreferences = instance ?: synchronized(lock) {
            instance ?: getSharedPreferences(context).also {
                instance = it
            }
        }

        private fun getSharedPreferences(context: Context): AppSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return AppSharedPreferences()
        }




    }

    fun saveTime(time: Long){
        sharedPreferences?.edit(commit = true){
            putLong(TIME,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(TIME,0)


}