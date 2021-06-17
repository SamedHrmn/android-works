package com.example.yemek_tarifleri

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import android.util.Log
import com.example.yemek_tarifleri.databinding.FragmentYemekDetayBinding


class DatabaseHelper(context: Context) {
    var yemekIsimList: ArrayList<String> = ArrayList()
    var yemekIdList: ArrayList<Int> = ArrayList()
    private var dbInstance: SQLiteDatabase? = null

    companion object {

        operator fun invoke(context: Context): DatabaseHelper {
            var instance: DatabaseHelper? = null
            if (instance == null) {
                instance = DatabaseHelper(context)
            }
            return instance
        }
    }

    init {

        dbInstance = context.openOrCreateDatabase("Yemekler", Context.MODE_PRIVATE, null)
        dbInstance!!.execSQL("CREATE TABLE IF NOT EXISTS yemekler (id INTEGER PRIMARY KEY, yemekismi VARCHAR,yemekdetayi VARCHAR,yemekresmi BLOB)")

    }

    fun addYemek(yemekAd: String, yemekDetay: String, yemekResmi: ByteArray) {

        try {
            val sqlString = "INSERT INTO yemekler (yemekismi,yemekdetayi,yemekresmi) VALUES (?,?,?)"
            val statement = dbInstance!!.compileStatement(sqlString)
            statement.bindString(1, yemekAd)
            statement.bindString(2, yemekDetay)
            statement.bindBlob(3, yemekResmi)
            statement.execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showAllYemek(adapter: ListeFragmentRecyclerAdapter) {
        val cursor = dbInstance!!.rawQuery("SELECT * FROM yemekler", null)
        val yemekIsmiIndex = cursor.getColumnIndex("yemekismi")
        val yemekIdIndex = cursor.getColumnIndex("id")

        yemekIsimList.clear()
        yemekIdList.clear()

        while (cursor.moveToNext()) {
            yemekIdList.add(cursor.getInt(yemekIdIndex))
            yemekIsimList.add(cursor.getString(yemekIsmiIndex))
        }
        Log.i("MESAJ", "DB LISTE" + yemekIsimList.toString())
        adapter.notifyDataSetChanged()
        cursor.close()
    }

    fun showSelectedItem(item_id: Int, binding: FragmentYemekDetayBinding) {
        val cursor = dbInstance!!.rawQuery("SELECT * FROM yemekler WHERE id = ?", arrayOf(item_id.toString()), null)
        val yemekIsmiIndex = cursor.getColumnIndex("yemekismi")
        val yemekDetayiIndex = cursor.getColumnIndex("yemekdetayi")
        val yemekResmiIndex = cursor.getColumnIndex("yemekresmi")

        while (cursor.moveToNext()) {
            binding.yemekIsimEditText.setText(cursor.getString(yemekIsmiIndex))
            binding.yemekDetayEditText.setText(cursor.getString(yemekDetayiIndex))
            val byteArray = cursor.getBlob(yemekResmiIndex)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            binding.yemekImageView.setImageBitmap(bitmap)

            Log.i("DATA", cursor.getString(yemekIsmiIndex))
        }

        cursor.close()
    }
}